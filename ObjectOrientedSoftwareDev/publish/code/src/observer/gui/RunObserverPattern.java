package observer.gui;
//[C] 2002 Sun Microsystems, Inc.---
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RunObserverPattern {
  public static void main(String[] arguments) {
    System.out.println("Example for the Observer pattern");
    System.out.println("This demonstration uses a central observable");
    System.out.println(" object to send change notifications to several");
    System.out.println(" JPanels in a GUI. Each JPanel is an Observer,");
    System.out.println(" receiving notifcations when there has been some");
    System.out.println(" change in the shared Task that is being edited.");
    System.out.println();

    System.out.println("Creating the ObserverGui");
    ObserverGui application = new ObserverGui();
    application.createGui();
  }
}

class Task {
  private String name = "";

  private String notes = "";

  private double timeRequired;

  public Task() {
  }

  public Task(String newName, String newNotes, double newTimeRequired) {
    name = newName;
    notes = newNotes;
    timeRequired = newTimeRequired;
  }

  public String getName() {
    return name;
  }

  public String getNotes() {
    return notes;
  }

  public double getTimeRequired() {
    return timeRequired;
  }

  public void setName(String newName) {
    name = newName;
  }

  public void setTimeRequired(double newTimeRequired) {
    timeRequired = newTimeRequired;
  }

  public void setNotes(String newNotes) {
    notes = newNotes;
  }

  public String toString() {
    return name + " " + notes;
  }
}

class TaskChangeObservable {
  private List<TaskChangeObserver> observers = new ArrayList<TaskChangeObserver>();

  public void addTaskChangeObserver(TaskChangeObserver observer) {
    if (!observers.contains(observer)) {
      observers.add(observer);
    }
  }

  public void removeTaskChangeObserver(TaskChangeObserver observer) {
    observers.remove(observer);
  }

  public void selectTask(Task task) {
    for (TaskChangeObserver o : observers) {
      o.taskSelected(task);
    }
  }

  public void addTask(Task task) {
    for (TaskChangeObserver o : observers) {
      o.taskSelected(task);
    }
  }

  public void updateTask(Task task) {
    for (TaskChangeObserver o : observers) {
      o.taskChanged(task);
    }
  }
}

interface TaskChangeObserver {
  public void taskAdded(Task task);
  public void taskChanged(Task task);
  public void taskSelected(Task task);
}

class TaskEditorPanel
  extends JPanel implements ActionListener, TaskChangeObserver
{
  private JPanel controlPanel, editPanel;

  private JButton add, update, exit;

  private JTextField taskName, taskNotes, taskTime;

  private TaskChangeObservable notifier;

  private Task editTask;

  public TaskEditorPanel(TaskChangeObservable newNotifier) {
    notifier = newNotifier;
    createGui();
  }

  public void createGui() {
    setLayout(new BorderLayout());
    editPanel = new JPanel();
    editPanel.setLayout(new GridLayout(3, 2));
    taskName = new JTextField(20);
    taskNotes = new JTextField(20);
    taskTime = new JTextField(20);
    editPanel.add(new JLabel("Task Name"));
    editPanel.add(taskName);
    editPanel.add(new JLabel("Task Notes"));
    editPanel.add(taskNotes);
    editPanel.add(new JLabel("Time Required"));
    editPanel.add(taskTime);

    controlPanel = new JPanel();
    add = new JButton("Add Task");
    update = new JButton("Update Task");
    exit = new JButton("Exit");
    controlPanel.add(add);
    controlPanel.add(update);
    controlPanel.add(exit);
    add.addActionListener(this);
    update.addActionListener(this);
    exit.addActionListener(this);
    add(controlPanel, BorderLayout.SOUTH);
    add(editPanel, BorderLayout.CENTER);
  }

  public void setTaskChangeObservable(TaskChangeObservable newNotifier) {
    notifier = newNotifier;
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
    if (source == add) {
      double timeRequired = 0.0;
      try {
        timeRequired = Double.parseDouble(taskTime.getText());
      } catch (NumberFormatException exc) {
      }
      notifier.addTask(new Task(taskName.getText(), taskNotes.getText(),
          timeRequired));
    } else if (source == update) {
      editTask.setName(taskName.getText());
      editTask.setNotes(taskNotes.getText());
      try {
        editTask
            .setTimeRequired(Double.parseDouble(taskTime.getText()));
      } catch (NumberFormatException exc) {
      }
      notifier.updateTask(editTask);
    } else if (source == exit) {
      System.exit(0);
    }

  }

  public void taskAdded(Task task) {
  }

  public void taskChanged(Task task) {
  }

  public void taskSelected(Task task) {
    editTask = task;
    taskName.setText(task.getName());
    taskNotes.setText(task.getNotes());
    taskTime.setText("" + task.getTimeRequired());
  }
}

class TaskHistoryPanel extends JPanel implements TaskChangeObserver {
  private JTextArea displayRegion;

  public TaskHistoryPanel() {
    createGui();
  }

  public void createGui() {
    setLayout(new BorderLayout());
    displayRegion = new JTextArea(10, 40);
    displayRegion.setEditable(false);
    add(new JScrollPane(displayRegion));
  }

  public void taskAdded(Task task) {
    displayRegion.append("Created task " + task + "\n");
  }

  public void taskChanged(Task task) {
    displayRegion.append("Updated task " + task + "\n");
  }

  public void taskSelected(Task task) {
    displayRegion.append("Selected task " + task + "\n");
  }
}

class TaskSelectorPanel
  extends JPanel implements ActionListener, TaskChangeObserver
{
  private JComboBox selector = new JComboBox();

  private TaskChangeObservable notifier;

  public TaskSelectorPanel(TaskChangeObservable newNotifier) {
    notifier = newNotifier;
    createGui();
  }

  public void createGui() {
    selector = new JComboBox();
    selector.addActionListener(this);
    add(selector);
  }

  public void actionPerformed(ActionEvent evt) {
    notifier.selectTask((Task) selector.getSelectedItem());
  }

  public void setTaskChangeObservable(TaskChangeObservable newNotifier) {
    notifier = newNotifier;
  }

  public void taskAdded(Task task) {
    selector.addItem(task);
  }

  public void taskChanged(Task task) {
  }

  public void taskSelected(Task task) {
  }
}

class ObserverGui {
  public void createGui() {
    JFrame mainFrame = new JFrame("Observer Pattern Example");
    Container content = mainFrame.getContentPane();
    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
    TaskChangeObservable observable = new TaskChangeObservable();
    TaskSelectorPanel select = new TaskSelectorPanel(observable);
    TaskHistoryPanel history = new TaskHistoryPanel();
    TaskEditorPanel edit = new TaskEditorPanel(observable);
    observable.addTaskChangeObserver(select);
    observable.addTaskChangeObserver(history);
    observable.addTaskChangeObserver(edit);
    observable.addTask(new Task());
    content.add(select);
    content.add(history);
    content.add(edit);
    mainFrame.addWindowListener(new WindowCloseManager());
    mainFrame.pack();
    mainFrame.setVisible(true);
  }

  private static class WindowCloseManager extends WindowAdapter {
    public void windowClosing(WindowEvent evt) {
      System.exit(0);
    }
  }
}
