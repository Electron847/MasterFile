//
//  FirstViewController.swift
//  Order My Sub - SB
//
//  Created by Xiaoping Jia on 12/23/19.
//  Copyright © 2019 DePaul University. All rights reserved.
//

import UIKit

let subs = [
    "Blockbuster",
    "Roast Beef",
    "Italian Special",
    "Corned Beef",
    "Big \"Al\" Italian",
    "Tuna",
    "Wise Guy",
    "Chicken Salad ",
    "Caputo",
    "Veggie",
    "Prosciutto",
    "Turkey Breast",
    "American"
]

class FirstViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {

    @IBOutlet weak var picker: UIPickerView!
    
    
    @IBAction func seleted(_ sender: UIButton) {
        let title = "Order My Sub"
        let message = "You have selected \(subs[picker.selectedRow(inComponent: 0)])"
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        
        // Create the action.
        let cancelAction = UIAlertAction(title: "Cancel", style: .destructive, handler: nil)
        let okayAction = UIAlertAction(title: "Confirm", style: .default, handler: nil)
        alertController.addAction(cancelAction)
        alertController.addAction(okayAction)
        present(alertController, animated: true, completion: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    // MARK: UIPickerViewDataSource
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return subs.count
    }
    
    // MARK: UIPickerViewDelegate
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return subs[row]
    }
    
}

