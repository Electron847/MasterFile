//
//  SecondViewController.swift
//  Order My Sub - SB
//
//  Created by Xiaoping Jia on 12/23/19.
//  Copyright © 2019 DePaul University. All rights reserved.
//

import UIKit

let sizes = [
    "6 inch",
    "8 inch",
    "10 inch",
    "12 inch",
    "16 inch",
    "3 foot"
]

class SecondViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {

    @IBOutlet weak var picker: UIPickerView!
    
    @IBAction func selected(_ sender: UIButton) {
        let title = "Order My Sub"
        let message = "You have selected \(sizes[picker.selectedRow(inComponent: 1)]) \(subs[picker.selectedRow(inComponent: 0)])"
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .actionSheet)
        
        // Create the action.
        let cancelAction = UIAlertAction(title: "Cancel", style: .destructive) { action in
            let cancelController = UIAlertController(title: "No Problem", message: "Select again.", preferredStyle: .alert)
            let okayAction = UIAlertAction(title: "Okay", style: .default, handler: nil)
            cancelController.addAction(okayAction)
            self.present(cancelController, animated: true, completion: nil)
        }
        let confirmAction = UIAlertAction(title: "Confirm", style: .default) { action in
            let dateFormat = DateFormatter()
            dateFormat.dateFormat = "MM/dd/yyyy hh:mma"
            let dateString = dateFormat.string(from: date)
            let okayController = UIAlertController(title: "Thank You!", message: "Your order will be ready by \(dateString).", preferredStyle: .alert)
            let okayAction = UIAlertAction(title: "Okay", style: .default, handler: nil)
            okayController.addAction(okayAction)
            self.present(okayController, animated: true, completion: nil)
        }
        alertController.addAction(cancelAction)
        alertController.addAction(confirmAction)
        present(alertController, animated: true, completion: nil)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    // MARK: UIPickerViewDataSource
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == 0 {
            return subs.count
        } else {
            return sizes.count
        }
    }
    
    // MARK: UIPickerViewDelegate
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == 0 {
            return subs[row]
        } else {
            return sizes[row]
        }
    }
    
}

