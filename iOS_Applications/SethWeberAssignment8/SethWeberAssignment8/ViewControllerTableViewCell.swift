//
//  ViewControllerTableViewCell.swift
//  SethWeberAssignment8
//
//  Created by Seth Weber on 3/8/20.
//  Copyright © 2020 DePaul University. All rights reserved.
//

import UIKit

class ViewControllerTableViewCell: UITableViewCell
{
    
    @IBOutlet weak var myImage: UIImageView!
    @IBOutlet weak var myLabel: UILabel!
    
    
    

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
