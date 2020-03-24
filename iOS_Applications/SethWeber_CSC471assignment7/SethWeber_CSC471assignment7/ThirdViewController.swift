//
//  ThirdViewController.swift
//  SethWeber_CSC471assignment7
//
//  Created by Seth Weber on 3/2/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class ThirdViewController: UIViewController {
    
    var nameText = ""
    @IBOutlet weak var stepperValue: UIStepper!
    @IBOutlet weak var input: UITextField!
    @IBOutlet weak var CH_Label: UILabel!
    @IBOutlet weak var stepperOutlet: UILabel!
    @IBOutlet weak var favChannelSegments: UISegmentedControl!
    
    @IBAction func stepperPressed(_ sender: UIStepper)
    {
        stepperOutlet.text = "TV Channel: \(Int(sender.value))"

    }
    
    @IBAction func save_cancelButtons(_ sender: AnyObject)
    {
        if sender.tag == 200 && input.text!.count == 3
        {
        newTitle[favChannelSegments.selectedSegmentIndex] = (Int(stepperValue.value),input.text!)
            
            print(newTitle)
            
        }
        if input.text!.count > 3 && sender.tag == 200
        {
            let title = "Ineligible Entry"
            let message = "Your Entry is Too Long"
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            
            // Create the action.
            let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
            let okayAction = UIAlertAction(title: "Confirm", style: .default, handler: nil)
            alertController.addAction(cancelAction)
            alertController.addAction(okayAction)
            present(alertController, animated: true, completion: nil)
            input.text = ""
            
        }

  
        if sender.tag == 100
        {
        input.text = ""
        }
    }
    
    
    @IBAction func channelSegments(_ sender: AnyObject)
    {
   
        
        if sender.titleForSegment(at: sender.selectedSegmentIndex) != nil
        {
            if sender.selectedSegmentIndex == 0
            {
               
            }
            if sender.selectedSegmentIndex == 1
            {
               
            }
            if sender.selectedSegmentIndex == 2
            {
             
            }
            if sender.selectedSegmentIndex == 3
            {
           
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        stepperOutlet.text = "TV Channel: 0"
    }
}

var newTitle:[(Int, String)] = [(14, "HBO"), (43, "CNN"), (39,"FX"), (9,"WGN")]


