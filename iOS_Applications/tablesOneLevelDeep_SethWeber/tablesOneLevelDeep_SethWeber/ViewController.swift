//
//  ViewController.swift
//  tablesOneLevelDeep_SethWeber
//
//  Created by Seth Weber on 3/9/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class ViewController: UIViewController
{

    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var descriptionLabel: UILabel!
    
    
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        titleLabel.text = interestsArray[myIndex]
        descriptionLabel.text = firstLevelArray[myIndex]
        imageView.image = UIImage(named: interestsArray[myIndex] + ".jpg")
    }


}

