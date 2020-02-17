//
//  ViewController.swift
//  SW_RemoteControliOS
//
//  Created by Seth Weber on 2/11/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//
//  Autolayout designed for iPhones 8 through 11 plus
//


import UIKit

class ViewController: UIViewController {
            
    var keyPadUsed = false
    var keyPadNumber: Int?
    
    
    @IBAction func keyPad(_ sender: UIButton)
    {
        keyPadNumber = Int(stepperAndKeyPadValue.text! + String(sender.tag-1))
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
        volumeSliderValue.text = "VL: \(Int(sender.value))"
    }
    
    @IBOutlet weak var stepperAndKeyPadValue: UILabel!
    
    @IBAction func stepperChanged(_ sender: UIStepper) {
        
        stepperAndKeyPadValue.text = "CH: \(Int(sender.value))"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    
}
