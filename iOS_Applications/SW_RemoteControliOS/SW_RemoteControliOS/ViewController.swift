//
//  ViewController.swift
//  SW_RemoteControliOS
//
//  Created by Seth Weber on 2/11/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
            
    var keyPadUsed = false
    var digits: Int = 0
    
    @IBAction func keyPad(_ sender: UIButton)
    {
        stepperAndKeyPadValue.text = stepperAndKeyPadValue.text! + String(sender.tag-1)
        
        
        
    }
    
    
    @IBOutlet weak var tvPowerSwitch: UISwitch!
    
    @IBOutlet weak var tvPowerLabel: UILabel!
    
    @IBAction func tvSwitchPressed(_ sender: UISwitch) {
        tvPowerLabel.text = "TV: " + (sender.isOn ? "On" : "Off")
        tvPowerSwitch.setOn(sender.isOn, animated: true)
    }
    
    
    @IBOutlet weak var volumeSliderValue: UILabel!
    
    
    @IBAction func volumeSliderMoved(_ sender: UISlider) {
        volumeSliderValue.text = "Volume: \(Int(sender.value))"
    }
    
  
    @IBOutlet weak var stepperAndKeyPadValue: UILabel!
    
    @IBAction func stepperChanged(_ sender: UIStepper) {
        stepperAndKeyPadValue.text = "Channel: \(Int(sender.value))"
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    
}
