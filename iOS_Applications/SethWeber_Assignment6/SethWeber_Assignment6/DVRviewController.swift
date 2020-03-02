//
//  DVRviewController.swift
//  SethWeber_Assignment6
//
//  Created by Seth Weber on 2/23/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class DVRviewController: UIViewController {
    
    @IBOutlet weak var DVRpowerSwitch: UISwitch!
    
    @IBOutlet weak var DVRpowerLabel: UILabel!    
    @IBOutlet weak var playButton: UIButton!
    @IBOutlet weak var backwardButton: UIButton!
    @IBOutlet weak var stopButton: UIButton!
    @IBOutlet weak var pauseButton: UIButton!
    @IBOutlet weak var recordButton: UIButton!
    @IBOutlet weak var forwardButton: UIButton!
    
    
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
    
    
 //   @IBAction func warningAction(_ sender: UIButton)
 //   {
//        let title = "WARNING"
//        let message = "Illegal Operation"
//        let alertController = UIAlertController(title:title,message: message, preferredStyle: .alert)
        
//        while playButton.isEnabled == false
 //       {
//            if sender == playButton
//            {
//                let cancelAction = UIAlertAction(title: "OK", style: .cancel, handler: nil)
 //               alertController.addAction(cancelAction)
 //               present(alertController,animated: true, completion: nil)
 //           }
  //      }
 //   }
    

    
    
    @IBAction func DVRswitchPressed(_ sender: UISwitch)
    {
        DVRpowerLabel.text = "DVR Power: " + (sender.isOn ? "On" : "Off")
        DVRpowerSwitch.setOn(sender.isOn, animated: true)
        
    }
    
    
    
    
    @IBOutlet weak var DVRstateValue: UILabel!
    
    @IBAction func controlPad(_ sender: UIButton)
    {
        
        if sender.tag == 20
        {
            let enabled = (sender.tag == 30 || sender.tag == 60)
            DVRstateValue.text = "DVR State: Play"
            
            stopButton.isEnabled = enabled
            recordButton.isEnabled = enabled
           
        }
        
        if sender.tag == 30
        {
            let enabled = (sender.tag == 20 || sender.tag == 50 || sender.tag == 60 || sender.tag == 70)
            DVRstateValue.text = "DVR State: Stop"
            playButton.isEnabled = enabled
            pauseButton.isEnabled = enabled
            backwardButton.isEnabled = enabled
            forwardButton.isEnabled = enabled
        }
        if sender.tag == 40
        {
            DVRstateValue.text = "DVR State: Pause"
        }
        if sender.tag == 50
        {
            DVRstateValue.text = "DVR State: Back"
        }
        if sender.tag == 60
        {
            DVRstateValue.text = "DVR State: Record"
        }
        if sender.tag == 70
        {
            DVRstateValue.text = "DVR State: Forward"
        }
        
    }
    
    
    
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func backToTV2(_ sender: UIBarButtonItem)
    {
        dismiss(animated: true, completion: nil)
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
