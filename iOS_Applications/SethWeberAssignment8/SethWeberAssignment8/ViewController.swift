//
//  ViewController.swift
//  SethWeberAssignment8
//
//  Created by Seth Weber on 3/8/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

let interests:[String] = ["myAnimals","myFood","myMusic","mySports"]
let myAnimals:[String] = ["Cat","Dog","Bird","Fish"]
let myFood:[String] = ["Tacos","Pizza","Italian Beef","Steak"]
let myMusic:[String] = ["Punk","Jazz","HipHop","Rock"]
let mySports:[String] = ["Baseball","Hockey","Football","Basketball"]

var myIndex:Int = 0
var myIndex2:Int = 0

class ViewController: UIViewController,UITableViewDelegate, UITableViewDataSource
{
    //let interests:[String] = ["myAnimals","myFood","myMusic","mySports"]

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return (interests.count)
    }
  
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        if tableView.tag == 91
        {
            
        }
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! ViewControllerTableViewCell
        cell.myImage.image = UIImage(named: (interests[indexPath.row] + ".jpg"))
        cell.myLabel.text = interests[indexPath.row]
        
        return(cell)
    }
    
    private func tableView(_ tableView: UITableViewCell, didSelectRowAt indexPath:IndexPath)
    {
        if tableView.tag == 91
        {
            
        }
        myIndex = indexPath.row
        performSegue(withIdentifier: "segue", sender: self)
    }
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    


}

