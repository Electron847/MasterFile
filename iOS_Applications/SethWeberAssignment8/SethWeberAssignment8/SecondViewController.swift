//
//  SecondViewController.swift
//  SethWeberAssignment8
//
//  Created by Seth Weber on 3/8/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController
{
  func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        //myIndex = IndexPath.init().count
        return (interests.count)
    }
  
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell1", for: indexPath) as! ViewControllerTableViewCell
        cell.myImage.image = UIImage(named: (interests[indexPath.row] + ".jpg"))
        cell.myLabel.text = interests[indexPath.row]
        
        return(cell)
    }
    private func tableView(_ tableView: UITableViewCell, didSelectRowAt indexPath:IndexPath)
    {
        myIndex = indexPath.row
       // performSegue(withIdentifier: "segue", sender: self)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
}
