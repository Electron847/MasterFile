//
//  DateViewController.swift
//  Order My Sub - SB
//
//  Created by Xiaoping Jia on 12/23/19.
//  Copyright © 2019 DePaul University. All rights reserved.
//

import UIKit

class DateViewController: UIViewController {

    @IBOutlet weak var options: UISegmentedControl!
    
    @IBOutlet weak var datePicker: UIDatePicker!
    
    @IBAction func optionSelected(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            datePicker.datePickerMode = .dateAndTime
        } else if sender.selectedSegmentIndex == 1 {
            datePicker.datePickerMode = .time
        }
    }
    
    @IBAction func datePicked(_ sender: UIButton) {
        let title = "Selected Time/Date"
        let message = "You have selected \(datePicker.date)"
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        
        // Create the action.
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
        let okayAction = UIAlertAction(title: "Confirm", style: .default) {
            _ in date = self.datePicker.date
        }
        alertController.addAction(cancelAction)
        alertController.addAction(okayAction)
        present(alertController, animated: true, completion: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}

var date = Date()
