//
//  ViewController.swift
//  navFun
//
//  Created by Seth Weber on 3/14/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

var globalCurrencyArray:[String] = []
var globalValueArray:[Double] = []
var globalConversionCurrencyArray:[String] = []
var globalConversionValueArray:[Double] = []
var baseRatePercentileArray:[Double] = []
//variables for cryptopath
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
        let url = URL(string: "http://data.fixer.io/api/latest?access_key=cdd30d50d55d64add24b1d2537f85361&format=1")
        let url1 = URL(string: "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=AED,AFN,ALL,CHF,USD,JPY,GBP,EUR,ETH,BCH,AUD,BSD,CLP,CNY,COP,EGP,HUF,INR,IRR,JOD,ETC&api_key=10661c33f6c5f75a85fdf6f938bd2e7f33e5ab5009a9afd52667ff179b78aeae")
        
        let task = URLSession.shared.dataTask(with: url!)
        { (data, response, error) in
            
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
                                baseRatePercentileArray.append(1/(value as? Double)!)
                                
                                //attempt at producing a global array
                                globalCurrencyArray.append((key as? String)!)
                                
                                //attempt at producing global value array
                                globalValueArray.append((value as? Double)!)
                                globalConversionCurrencyArray.append((key as? String)!)
                                //produce global conversion value array
                                globalConversionValueArray.append((value as? Double)!)
                            }
                        }
                    }
                    catch
                    {
                        
                    }
                }
            }
          
            //get Crypto information
            let task1 = URLSession.shared.dataTask(with: url1!)
            { (data, response, error) in
            
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
        task1.resume()
    }
        task.resume()
 }
    
}
