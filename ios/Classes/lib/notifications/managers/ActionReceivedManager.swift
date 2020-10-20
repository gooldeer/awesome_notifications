//
//  ActionReceivedManager.swift
//  awesome_notifications
//
//  Created by Sehii Moisa on 20/10/20.
//

import Foundation

public class ActionReceivedManager {
    
    static let shared:SharedManager = SharedManager(tag: Definitions.SHARED_ACTIONS)
    
    public static func removeAction(id:Int) -> Bool {
        return shared.remove(referenceKey: String(id));
    }

    public static func listActions() -> [ActionReceived] {
        var returnedList:[ActionReceived] = []
        let dataList = shared.getAllObjects()
        
        for data in dataList {
            let received:ActionReceived = ActionReceived(nil).fromMap(arguments: data) as! ActionReceived
            returnedList.append(received)
        }
        
        return returnedList
    }

    public static func saveAction(received:ActionReceived) {
        shared.set(received.toMap(), referenceKey: String(describing: received.id))
    }

    public static func getActionByKey(id:Int) -> ActionReceived? {
        return ActionReceived(nil).fromMap(arguments: shared.get(referenceKey: String(id))) as? ActionReceived
    }

    public static func cancelAllActions() {
        let receivedList = shared.getAllObjects();
        
        for received:[String:Any?] in receivedList {
            cancelAction(id: received["id"] as! Int);
        }
    }

    public static func cancelAction(id:Int) {
        _ = shared.remove(referenceKey: String(id))
    }
    
}
