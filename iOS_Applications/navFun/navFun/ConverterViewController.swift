//
//  ConverterViewController.swift
//  navFun
//
//  Created by Seth Weber on 3/14/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

var tableViewArray1:[(String,Double,String,Double)] = []

class ConverterViewController: UIViewController,UIPickerViewDelegate, UIPickerViewDataSource
{
    var activeCurrency:Double = 0
    var conversionActive:Double = 0
    var baseRatePercent:Double = 0
    var baseRatePercent1:Double = 0
    var tableViewString:String = ""
    var tableViewString1:String = ""
    var tableViewDouble:Double = 0
    var tableViewDouble1:Double = 0
    
    @IBOutlet weak var myTextField: UITextField!
    @IBOutlet weak var output: UILabel!
    @IBOutlet weak var myPickerView: UIPickerView!
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int
    {
        return 1
    }
       
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int
    {
        globalCurrencyArray.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String?
    {
        return globalCurrencyArray[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int)
    {
      if pickerView.tag == 1000
      {
          tableViewString = globalCurrencyArray[row]
          baseRatePercent = baseRatePercentileArray[row]
          activeCurrency = globalValueArray[row]
      }
      if pickerView.tag == 2000
      {
          tableViewString1 = globalCurrencyArray[row]
          baseRatePercent1 = baseRatePercentileArray[row]
          conversionActive = globalConversionValueArray[row]
      }
    }
    
    
    @IBAction func convertButton(_ sender: Any)
    {
        if (myTextField.text != "" )
            
        {
            tableViewDouble = Double(myTextField.text!)!
            output.text = String(Double(myTextField.text!)! * ((conversionActive * baseRatePercent) / (activeCurrency)) / baseRatePercent)
            tableViewDouble1 = Double(output.text!)!
            tableViewArray1.append((tableViewString,tableViewDouble, tableViewString1,tableViewDouble1))
        }
    }
    
    @IBOutlet weak var globalArrayOutlet: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
