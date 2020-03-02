//
//  ViewController.swift
//  SethWeber_ShoppingList
//
//  Created by Seth Weber on 2/19/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var myShopList: UILabel!
    
    @IBOutlet weak var descriptionField: UITextField!
    
    @IBOutlet weak var itemQuantityField: UITextField!
    
    @IBOutlet var myTextFields: [UITextField]!
    
    
    
    
      
    @IBAction func newListButton(_ sender: UIButton)
    {
         myShopList.text = "Empty List"
            for tf in myTextFields {
                tf.text = ""
            }
        
    }
    
    @IBAction func newItemButton(_ sender: Any) {
        for tf in myTextFields
        {
            tf.text = ""
        }
    }
    
    
    @IBAction func addItemButton(_ sender: UIButton) {
    }
    
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
    }
    
    @IBAction func quantityFieldExit(_ sender: UITextField) {
        sender.resignFirstResponder()
    }
    
    
    @IBAction func descriptionFieldExit(_ sender: UITextField) {
        sender.resignFirstResponder()
    }
    
    @IBAction func backgroundExit(_ sender: UIControl) {
    }
    
}

