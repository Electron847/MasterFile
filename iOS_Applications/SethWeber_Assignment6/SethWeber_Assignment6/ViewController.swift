//
//  ViewController.swift
//  SethWeber_Assignment6
//
//  Created by Seth Weber on 2/23/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class ViewController: UIViewController
{
    var keyPadNumber: Int?
    
    @IBOutlet weak var tvPowerSwitch: UISwitch!
    @IBOutlet weak var tvPowerLabel: UILabel!
    
    @IBOutlet weak var stepperAndKeyPadValue: UILabel!
    
    @IBOutlet weak var volumeSliderValue: UILabel!
    
    
    @IBOutlet weak var sliderOutlet: UISlider!
    
    @IBOutlet weak var stepperOutlet: UIStepper!
    
    
    @IBOutlet var keyPadOutlet: [UIButton]!
    
    
    
    
    
    
    @IBAction func volumeSliderMoved(_ sender: UISlider)
    {
        volumeSliderValue.text = "TV Volume: \(Int(sender.value))"
    }
    
    @IBAction func tvSwitchPressed(_ sender: UISwitch)
    {
        tvPowerLabel.text = "TV Power: " + (sender.isOn ? "On" : "Off")
        tvPowerSwitch.setOn(sender.isOn, animated: true)
        
        let enabled = (sender.isOn)
        //fill with code to disable buttons when tv is off
        sliderOutlet.isEnabled = enabled
        stepperOutlet.isEnabled = enabled
        for tb in keyPadOutlet
        {
            tb.isEnabled = enabled
        }
        
    }
    
    
    @IBAction func keyPad(_ sender: UIButton)
    {
        keyPadNumber = Int(stepperAndKeyPadValue.text! + String(sender.tag-1))
        stepperAndKeyPadValue.text = stepperAndKeyPadValue.text! + String(sender.tag-1)
        
        
    }
    
    @IBAction func stepperChanged(_ sender: UIStepper)
    {
        stepperAndKeyPadValue.text = "TV Channel: \(Int(sender.value))"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    
    
    
    
    
    
    
    
    


}

