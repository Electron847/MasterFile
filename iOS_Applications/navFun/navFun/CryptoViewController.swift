//
//  CryptoViewController.swift
//  navFun
//
//  Created by Seth Weber on 3/15/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit
var cryptoInfo:[(String, Double)] = []
class CryptoViewController: UIViewController
{
    var cryptoNames:[String] = []
    var cryptoValues:[Double] = []

    @IBOutlet weak var outputLabel: UILabel!
    override func viewDidLoad()
    {
        super.viewDidLoad()
        outputLabel.numberOfLines = currencyNames.count
        for (key,value) in myCurrency
        {
            cryptoNames.append((key as? String)!)
            cryptoValues.append((value as? Double)!)
            outputLabel.text?.append("\n Current \(key as! String) value for BTC base is \(value as! Double)")            
        }
    }
}
