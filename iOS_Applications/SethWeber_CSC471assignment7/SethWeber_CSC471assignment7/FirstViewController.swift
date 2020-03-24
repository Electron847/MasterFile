//
//  FirstViewController.swift
//  SethWeber_CSC471assignment7
//
//  Created by Seth Weber on 3/2/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController {
    
    var finalName = ""
    var keyPadNumber: Int?
    @IBOutlet weak var tvPowerSwitch: UISwitch!
    @IBOutlet weak var tvPowerLabel: UILabel!
    @IBOutlet weak var tvVolumeLabel: UILabel!
    @IBOutlet weak var stepperAndKeyPadLabel: UILabel!
    @IBOutlet weak var volumeSlider: UISlider!
    @IBOutlet weak var channelStepper: UIStepper!
    @IBOutlet var keyPadButtons: [UIButton]!
    @IBOutlet weak var favChannelSegments: UISegmentedControl!
    
    
    @IBAction func volumeSliderMoved(_ sender: UISlider)
    {
        tvVolumeLabel.text = "TV Volume: \(Int(sender.value))"
    }
    
    @IBAction func tvSwitchPressed(_ sender: UISwitch)
    {
        tvPowerLabel.text = "TV Power: " + (sender.isOn ? "On" : "Off")
        tvPowerSwitch.setOn(sender.isOn, animated: true)
        
        let enabled = (sender.isOn)
        //fill with code to disable buttons when tv is off
        favChannelSegments.isEnabled = enabled
        volumeSlider.isEnabled = enabled
        channelStepper.isEnabled = enabled
        for tb in keyPadButtons
        {
            tb.isEnabled = enabled
        }
    }
    
    
    @IBAction func keyPadPressed(_ sender: UIButton)
    {
        keyPadNumber = Int(stepperAndKeyPadLabel.text! + String(sender.tag-1))
        stepperAndKeyPadLabel.text =  stepperAndKeyPadLabel.text! + String(sender.tag-1)
    }
    
    
    
    @IBAction func stepperPressed(_ sender: UIStepper)
    {
        stepperAndKeyPadLabel.text = "TV Channel: \(Int(sender.value))"
    }
    
    
    @IBAction func favChannelSegments(_ sender: UISegmentedControl)
    {
        if sender.titleForSegment(at: sender.selectedSegmentIndex) != nil
        {
            if sender.selectedSegmentIndex == 0 
            {
                print(stepperAndKeyPadLabel.text!.count)
                stepperAndKeyPadLabel.text = "TV Channel: \(newTitle[0].0)"
                
              //  stepperAndKeyPadLabel.text = "TV Channel: \(newTitle[0].0)"
            }
            if sender.selectedSegmentIndex == 1
            {
                stepperAndKeyPadLabel.text = "TV Channel: \(newTitle[1].0)"
            }
            if sender.selectedSegmentIndex == 2
            {
                stepperAndKeyPadLabel.text = "TV Channel: \(newTitle[2].0)"
            }
            if sender.selectedSegmentIndex == 3
            {
                stepperAndKeyPadLabel.text = "TV Channel: \(newTitle[3].0)"
            }
           
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        tvPowerLabel.text = "TV Power: On"
        tvVolumeLabel.text = "TV Volume: 50"
        stepperAndKeyPadLabel.text = "TV Channel: 3"
       
    }

   override func viewDidAppear(_ animated:Bool)
   {
    for i in 0...3
    {
            favChannelSegments.setTitle(newTitle[i].1, forSegmentAt: i)
            stepperAndKeyPadLabel.text = "TV Channel: \(String(newTitle[i].0))"
    
    }
   }
}

