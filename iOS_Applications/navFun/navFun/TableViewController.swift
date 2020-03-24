//
//  TableViewController.swift
//  navFun
//
//  Created by Seth Weber on 3/14/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

var myIndex = 0
var cellArray:[String] = []

class TableViewController: UIViewController, UITableViewDelegate,UITableViewDataSource
{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return tableViewArray1.count
    }
    
     func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
     {
               let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)

        cell.textLabel?.text = tableViewArray1[indexPath.row].0
        cellArray.append(tableViewArray1[indexPath.row].0)
        
        cell.textLabel?.text?.append(" converted to ")
        
        cellArray.append(" converted to ")
        cell.textLabel?.text?.append(tableViewArray1[indexPath.row].2)
               return cell
      }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)
    {
        myIndex = indexPath.row
        performSegue(withIdentifier: "segue", sender: self)
    }

    override func viewDidLoad()
    {
        super.viewDidLoad()
        print(tableViewArray1)
    }
}
