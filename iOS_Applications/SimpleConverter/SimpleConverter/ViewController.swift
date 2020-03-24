//
//  ViewController.swift
//  SimpleConverter
//
//  Created by Seth Weber on 3/2/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit


class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource
{
      var myCurrency:[String] = []
      var myValues:[Double] = []
      var conversionCurrency:[String] = []
      var conversionValues:[Double] = []
      var activeCurrency:Double = 0
      var conversionActive:Double = 0
      
      //OBJECTS
      @IBOutlet weak var input: UITextField!
      @IBOutlet weak var pickerView: UIPickerView!
      @IBOutlet weak var output: UILabel!
    
    
      
      //CREATING PICKER VIEW
      func numberOfComponents(in pickerView: UIPickerView) -> Int
      {
          return 1
      }
      
      func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int
      {
          return myCurrency.count
      }
      
      func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String?
      {
          //print("HELLO THERE" + myCurrency[row])
        //print(conversionCurrency[row])
        //print(myCurrency[row])
        
          return myCurrency[row]
      }
      
      func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int)
      {
        if pickerView.tag == 1000
        {
            activeCurrency = myValues[row]
            print(activeCurrency)
        }
        if pickerView.tag == 2000
        {
            conversionActive = conversionValues[row]
            print(conversionActive)
        }
          //activeCurrency = myValues[row]
        //print(conversionCurrency[row])
        //print(myCurrency[row])
      }
    
    
    
    
    
   
    
  

      
      //BUTTON
      @IBAction func action(_ sender: AnyObject)
      {
          if (input.text != "")
          {
            output.text = String(Double(input.text!)! * activeCurrency / conversionActive)
            
            //print(myCurrency.indices)
            //print(input.text!)
            //print(myCurrency)
            print(conversionActive)
            print(activeCurrency)
          }
      }
    
      override func viewDidLoad()
      {
          super.viewDidLoad()
          
          //GETTING DATA
          let url = URL(string: "http://data.fixer.io/api/latest?access_key=cdd30d50d55d64add24b1d2537f85361&format=1")
          
          let task = URLSession.shared.dataTask(with: url!) { (data, response, error) in
              
              if error != nil
              {
                  print ("ERROR")
              }
              else
              {
                  if let content = data
                  {
                      do
                      {
                          let myJson = try JSONSerialization.jsonObject(with: content, options: JSONSerialization.ReadingOptions.mutableContainers) as AnyObject
                          
                          if let rates = myJson["rates"] as? NSDictionary
                          {
                              for (key, value) in rates
                              {
                                  self.myCurrency.append((key as? String)!)
                                  self.myValues.append((value as? Double)!)
                                self.conversionCurrency.append((key as? String)!)
                                self.conversionValues.append((value as? Double)!)
                              }
                            print(self.myCurrency)
                            print(self.myValues)
                          }
                      }
                      catch
                      {
                          
                      }
                  }
              }
              //self.pickerView.reloadAllComponents()
          }
        self.pickerView.reloadAllComponents()
          task.resume()
      }

      override func didReceiveMemoryWarning()
      {
          super.didReceiveMemoryWarning()
          // Dispose of any resources that can be recreated.
      }
}
        
   //    let url1 = URL(string: "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=AED,AFN,ALL,USD,JPY,GBP,EUR,ETH,BCH,ETC&api_key=10661c33f6c5f75a85fdf6f938bd2e7f33e5ab5009a9afd52667ff179b78aeae")
   //     let task1 = URLSession.shared.dataTask(with: url1!)
    //    {(data1, response, error1) in
   //         if error1 != nil
   //         {
   //             print("Crypto Error!")
    //        }
   //         else
   //         {
   //             if let content1 = data1
   //             {
   //                 do //Array
   //                 {
   //                     let myList = try JSONSerialization.jsonObject(with: content1, options: JSONSerialization.ReadingOptions.mutableContainers) as AnyObject
   //                     print("Base is 1 BTC")
   //                     print(myList)
   //                     if let rates1 = myList["rates1"] as? NSDictionary
  //                      {
  //                        //  print(rates1[0]!)
   //                     }
  //                  }
  //                  catch
  //                  {
  //
  //                  }
  //              }
  //          }
   //     }
 //       task1.resume()
        


