//
//  TableViewController.swift
//  tablesOneLevelDeep_SethWeber
//
//  Created by Seth Weber on 3/9/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

let interestsArray = ["Tacos","Dogs","HipHop","Graffiti"]
let firstLevelArray = ["The Atontonilco Taco @ Atontonilco 1637 S Blue Island Avenue in Pilsen","The Cane Corso Bull Mastiff","Mos Def born Dante Terrell Smith from Brooklyn","LA OG Style started in the 1990's"]
var myIndex = 0

class TableViewController: UITableViewController {

    // MARK: - Table view data source

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return interestsArray.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)

        cell.textLabel?.text = interestsArray[indexPath.row]

        return cell
    }
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)
    {
        myIndex = indexPath.row
        performSegue(withIdentifier: "segue", sender: self)
        
    }

}
