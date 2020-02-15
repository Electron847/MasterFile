//
//  ViewController.swift
//  Calculator
//
//  Created by Seth Weber on 2/4/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var numberPresented: Double = 0
    var anteNumber: Double = 0
    var mathTime = false
    var operatorTag = 0

    @IBOutlet weak var label: UILabel!
    
    @IBAction func numbers(_ sender: UIButton)
    {
        if mathTime == true
        {
            label.text = String(sender.tag-1)
            numberPresented = Double(label.text!)!
            mathTime = false
        }
        else
        {
            label.text = label.text! + String(sender.tag-1)
            numberPresented = Double(label.text!)!
        }
    }
    
    
    @IBAction func buttons(_ sender: UIButton)
    {
        if label.text != "" && sender.tag != 11 && sender.tag != 16
        {
            anteNumber = Double(label.text!)!
            
            if sender.tag == 12 //Divide
            {
                label.text = "/"
            }
            else if sender.tag == 13 //Multiply
            {
                label.text = "x"
            }
            else if sender.tag == 14 //Plus
            {
                label.text = "+"
            }
            else if sender.tag == 15 //Minus
            {
                label.text = "-"
            }
            operatorTag = sender.tag
            mathTime = true
        }
        else if sender.tag == 16
        {
            if operatorTag == 12
            {
                label.text = String(anteNumber / numberPresented)
            }
            if operatorTag == 13
            {
                label.text = String(anteNumber * numberPresented)
            }
            if operatorTag == 14
            {
                label.text = String(anteNumber + numberPresented)
            }
            if operatorTag == 15
            {
                label.text = String(anteNumber - numberPresented)
            }
        }
        else if sender.tag == 11
        {
            label.text = ""
            anteNumber = 0
            numberPresented = 0
            operatorTag = 0
        }
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

}

