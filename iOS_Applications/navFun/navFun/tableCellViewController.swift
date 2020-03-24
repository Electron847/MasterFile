//
//  tableCellViewController.swift
//  navFun
//
//  Created by Seth Weber on 3/15/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class tableCellViewController: UIViewController
{
    @IBOutlet weak var firstCellOutput: UILabel!
    override func viewDidLoad()
    {
        
        super.viewDidLoad()
        firstCellOutput.numberOfLines = cellArray.count
    firstCellOutput.text?.append("\(String(tableViewArray1[myIndex].1)) ")
        
        firstCellOutput.text?.append( tableViewArray1[myIndex].0)
        
        firstCellOutput.text?.append(" was converted into \(tableViewArray1[myIndex].3) ")
        
        firstCellOutput.text?.append( tableViewArray1[myIndex].2)
    }
}
