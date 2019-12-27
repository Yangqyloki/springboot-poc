use b2b_commerce_org

db.b2b_commerce_org.unit.insert({"unitId":"Rustic","unitName":"Rustic","parentUnit":"","approvalProcess":"Escalation Approval with Merchant Check","path":"/Rustic","administrator":{"title":"ms","firstName":"Linda","lastName":"Wolf","email":"linda.wolf@rustic-hw.com","parentUnit":"Rustic","roles":["b2badmingroup"]}})

db.b2b_commerce_org.unit.insert({"unitId":"Rustic Retail","unitName":"Rustic Retail","parentUnit":"Rustic","approvalProcess":"Escalation Approval with Merchant Check","path":"/Rustic/Rustic Retail"})

db.b2b_commerce_org.unit.insert({"unitId":"Custom Retail","unitName":"Custom Retail","parentUnit":"Rustic Retail","approvalProcess":"Escalation Approval with Merchant Check","path":"/Rustic/Rustic Retail/Custom Retail"})

db.b2b_commerce_org.unit.insert({"unitId":"Rustic Services","unitName":"Rustic Services","parentUnit":"Rustic","approvalProcess":"Escalation Approval with Merchant Check","path":"/Rustic/Rustic Services"})

db.b2b_commerce_org.unit.insert({"unitId":"Services East","unitName":"Services East","parentUnit":"Rustic Services","approvalProcess":"Escalation Approval with Merchant Check","path":"/Rustic/Rustic Services/Services East"})

db.b2b_commerce_org.unit.insert({"unitId":"Services West","unitName":"Services West","parentUnit":"Rustic Services","approvalProcess":"Escalation Approval with Merchant Check","path":"/Rustic/Rustic Services/Services West"})


db.b2b_commerce_org.unit.insert({"unitId":"SAP","unitName":"SAP","parentUnit":"","approvalProcess":"Escalation Approval with Merchant Check","path":"/SAP"})

db.b2b_commerce_org.unit.insert({"unitId":"SAP Canada","unitName":"SAP Canada","parentUnit":"SAP","approvalProcess":"Escalation Approval with Merchant Check","path":"/SAP/SAP Canada"})

db.b2b_commerce_org.unit.insert({"unitId":"SAP China","unitName":"SAP China","parentUnit":"SAP","approvalProcess":"Escalation Approval with Merchant Check","path":"/SAP/SAP China"})

db.b2b_commerce_org.unit.insert({"unitId":"SAP ChengDu","unitName":"SAP ChengDu","parentUnit":"SAP China","approvalProcess":"Escalation Approval with Merchant Check","path":"/SAP/SAP China/SAP ChengDu"})

db.b2b_commerce_org.unit.insert({"unitId":"SAP ShangHai","unitName":"SAP ShangHai","parentUnit":"SAP China","approvalProcess":"Escalation Approval with Merchant Check","path":"/SAP/SAP China/SAP ShangHai"})

db.b2b_commerce_org.address.insert({"unitId":"Rustic","country":"United States","city":"New York","street":"700 E 50th Street"})

db.b2b_commerce_org.address.insert({"unitId":"Services West","country":"United States","city":"Chicago","street":"999 South Wacker Drive"})

db.b2b_commerce_org.address.insert({"unitId":"Rustic Retail","country":"中国","city":"深圳","street":"华强北857号"})

db.b2b_commerce_org.address.insert({"unitId":"Rustic Retail","country":"中国","city":"北京","street":"王府井151号"})

db.b2b_commerce_org.address.insert({"unitId":"Services East","country":"United States","city":"Houston","street":"179 Bellevue Square"})