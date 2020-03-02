//
//  ViewController.swift
//  sw_ShoppingList
//
//  Created by Seth Weber on 2/18/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet var Fields: [UITextField]!
    @IBOutlet weak var ShoppingList: UILabel!
    @IBOutlet weak var Quantity: UITextField!
    @IBOutlet weak var Description: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBAction func NewList(_ sender: UIButton) {
        ShoppingList.text = "List Empty"
        for tf in Fields {
            tf.text = ""
        }
    }
    @IBAction func NewItem(_ sender: UIButton) {
        for tf in Fields {
            tf.text = ""
        }
    }
    
    @IBAction func AddItem(_ sender: UIButton) {
        if Description.text == "" || Description.text == nil{
            return
        }
        if Quantity.text == "" || Int(Quantity.text!) == nil || Quantity.text == nil{
            return
        }
        
        //new item
        if ShoppingList.text == "List Empty" {
            ShoppingList.text = "\(Int(Quantity.text!)!)x \(Description.text!)"
        }
        else {
            ShoppingList.text! += "\n\(Int(Quantity.text!)!)x \(Description.text!)"
        }
    }
    
    
    @IBAction func QuantEditExit(_ sender: UITextField) {
        sender.resignFirstResponder()
    }
    @IBAction func ExitEditing(_ sender: UITextField) {
        sender.resignFirstResponder()
    }
    
    @IBAction func TouchedBackground(_ sender: UIControl) {
        for fd in Fields {
            fd.resignFirstResponder()
        }
    }
    
}

