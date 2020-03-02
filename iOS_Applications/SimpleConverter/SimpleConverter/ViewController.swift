//
//  ViewController.swift
//  SimpleConverter
//
//  Created by Seth Weber on 3/2/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let url = URL(string: "http://data.fixer.io/api/latest?access_key=cdd30d50d55d64add24b1d2537f85361&format=1")
        
        let task = URLSession.shared.dataTask(with: url!)
        {(data, response, error) in
            if error != nil
            {
                print("ERROR!")
            }
            else
            {
                if let content = data
                {
                    do
                    {//Array
                        let myJson = try JSONSerialization.jsonObject(with: content, options: JSONSerialization.ReadingOptions.mutableContainers) as AnyObject
                        if let rates = myJson["rates"] as? NSDictionary
                        {
                            print(rates)
                        }
                    }
                    catch {}
                }
            }
        
        }
        task.resume()
    }

}

