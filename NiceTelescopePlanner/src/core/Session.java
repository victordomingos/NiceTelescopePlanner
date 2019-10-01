/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Date;

/**
 *
 * @author victor
 */
public class Session {
    int session_id;
    int location_id;
    Date date;
    int bookmarked_targets = 0;
    int seen_targets = 0;
    String description = "";
    String notes = "";
    
}
