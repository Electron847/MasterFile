//
//  SecondViewController.swift
//  SethWeber_CSC471assignment7
//
//  Created by Seth Weber on 3/2/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {
    
    @IBOutlet weak var dvrPowerSwitch: UISwitch!
    @IBOutlet weak var dvrPowerLabel: UILabel!
    @IBOutlet weak var dvrStateLabel: UILabel!
    @IBOutlet weak var playButton: UIButton!
    @IBOutlet weak var stopButton: UIButton!
    @IBOutlet weak var pauseButton: UIButton!
    @IBOutlet weak var backwardButton: UIButton!
    @IBOutlet weak var recordButton: UIButton!
    @IBOutlet weak var forwardButton: UIButton!

    @IBAction func dvrSwitchPressed(_ sender: UISwitch)
    {
        dvrPowerLabel.text = "DVR Power: " + (sender.isOn ? "On" : "Off");dvrPowerSwitch.setOn(sender.isOn, animated: true)
    }
    
    @IBAction func disableButtons(_ sender: UISwitch)
    {
        let enabled = (sender.isOn)
        playButton.isEnabled = enabled
        backwardButton.isEnabled = enabled
        pauseButton.isEnabled = enabled
        recordButton.isEnabled = enabled
        forwardButton.isEnabled = enabled
        stopButton.isEnabled = enabled
    }
    
    @IBAction func controlPad(_ sender: UIButton)
    {
        if sender.tag == 20 && dvrStateLabel.text != "DVR State: Record"
        {
            dvrStateLabel.text = "DVR State: Play"
        }
        
        if sender.tag == 20 && dvrStateLabel.text == "DVR State: Record"
        {
            let title = "Function Not Allowed"
            let message = "Decide to Confirm or Force"
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            let cancelAction = UIAlertAction(title: "Cancel", style: .default) {(action) in
                self.dvrStateLabel.text = "DVR State: Record"
            }
            let okayAction = UIAlertAction(title: "Force", style: .default) {(action) in
                self.dvrStateLabel.text = "DVR State: Play"
            }
            alertController.addAction((cancelAction))
            alertController.addAction(okayAction)
            present(alertController, animated: true, completion: nil)
        }
        
        if sender.tag == 30 && dvrStateLabel.text == "DVR State: Play"
        {
            let title = "Function Not Allowed"
            let message = "Decide to Confirm or Force"
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            let cancelAction = UIAlertAction(title: "Cancel", style: .default) {(action) in
                self.dvrStateLabel.text = "DVR State: Play"
            }
            let okayAction = UIAlertAction(title: "Force", style: .default) {(action) in
                self.dvrStateLabel.text = "DVR State: Stop"
            }
            alertController.addAction((cancelAction))
            alertController.addAction(okayAction)
            present(alertController, animated: true, completion: nil)
            
        }
        
        if sender.tag == 40 && dvrStateLabel.text == "DVR State: Stop"
        {
            let title = "Function Not Allowed"
            let message = "Decide to Confirm or Force"
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            let cancelAction = UIAlertAction(title: "Cancel", style: .default) {(action) in
                self.dvrStateLabel.text = "DVR State: Stop"
            }
            let okayAction = UIAlertAction(title: "Force", style: .default) {(action) in
                self.dvrStateLabel.text = "DVR State: Pause"
            }
            alertController.addAction((cancelAction))
            alertController.addAction(okayAction)
            present(alertController, animated: true, completion: nil)
        }
        
        if sender.tag == 30
        {
            dvrStateLabel.text = "DVR State: Stop"
        }
        if sender.tag == 40 && dvrStateLabel.text == "DVR State: Play"
        {
            dvrStateLabel.text = "DVR State: Pause"
        }
        if sender.tag == 50 && dvrStateLabel.text == "DVR State: Play"
        {
            dvrStateLabel.text = "DVR State: Back"
        }
        if sender.tag == 60 && dvrStateLabel.text == "DVR State: Stop"
        {
            dvrStateLabel.text = "DVR State: Record"
        }
        if sender.tag == 60 && dvrStateLabel.text == "DVR State: Play"
        {
            //dvrStateLabel.text = "DVR State: Record"
            let title = "Function Not Allowed"
            let message = "Decide to Confirm or Force"
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            let cancelAction = UIAlertAction(title: "Cancel", style: .default) {(action) in
                self.dvrStateLabel.text = "DVR State: Play"
            }
            let okayAction = UIAlertAction(title: "Force", style: .default) {(action) in
                self.dvrStateLabel.text = "DVR State: Record"
            }
            alertController.addAction((cancelAction))
            alertController.addAction(okayAction)
            present(alertController, animated: true, completion: nil)
        }
        if sender.tag == 70 && dvrStateLabel.text == "DVR State: Play"
        {
            dvrStateLabel.text = "DVR State: Forward"
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        dvrStateLabel.text = "DVR State: Stop"
    }
}

