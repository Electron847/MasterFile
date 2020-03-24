//
//  ViewController.swift
//  CryptoConverter
//
//  Created by Seth Weber on 3/15/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

var myCurrency:NSDictionary = [:]

var currencyNames:[String] = []
var currencyValues:[Double] = []
var conversionCurrencyNames:[String] = []
var conversionCurrencyValues:[Double] = []


class ViewController: UIViewController
{
         var activeCurrency:Double = 0
         var conversionActive:Double = 0
    
   override func viewDidLoad()
          {
              super.viewDidLoad()
              
              //GETTING DATA
            let url = URL(string: "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=AED,AFN,ALL,CHF,USD,JPY,GBP,EUR,ETH,BCH,ETC&api_key=10661c33f6c5f75a85fdf6f938bd2e7f33e5ab5009a9afd52667ff179b78aeae")
            
            //https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC&tsyms=USD,EUR
              
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
                              
                            print(myJson)
                            myCurrency = myJson as! NSDictionary
                            print(myCurrency)
                            for (key, value) in myCurrency
                            {
                                currencyNames.append((key as? String)!)
                                currencyValues.append((value as? Double)!)
                                print(key, " and ", value)
                            }
                          }
                          catch
                          {
                              
                          }
                      }
                  }
              }
              task.resume()
          }

          override func didReceiveMemoryWarning()
          {
              super.didReceiveMemoryWarning()
              // Dispose of any resources that can be recreated.
          }

    }
