package com.honeywell.faa

case class recordFAA(xmlMessage:String ,col1: String, col2: String) {

}



case class joinTable(Source: String, SourceDescription: String)

case object table1 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("Active", "Active") => 3

      case joinTable("Terminated", "Cancelled") => 5

      case joinTable("Pending", "Pending") => 6
      case joinTable("Passive", "Passive") => 7
    }

  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("Active", "Active") => "Active"

      case joinTable("Terminated", "Cancelled") => "Cancelled"

      case joinTable("Pending", "Pending") => "Pending"
      case joinTable("Passive", "Passive") => "Passive"
    }
  }
}

case object table2 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("A", "Tracon Airspace Arrival") => 1

      case joinTable("D", "Tracon Airspace Departure") => 2

      case joinTable("E", "Tracon Airspace Enroute") => 3
      case joinTable("P", "Tracon Airspace Enroute") => 3
    }

  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("A", "Tracon Airspace Arrival") => "Tracon Airspace Arrival"

      case joinTable("D", "Tracon Airspace Departure") => "Tracon Airspace Departure"

      case joinTable("E", "Tracon Airspace Enroute") => "Tracon Airspace Enroute"
      case joinTable("P", "Tracon Airspace Enroute") => "Tracon Airspace Enroute"
    }
  }
}

case object table3 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("FP", "TAIS Track and Flight Plan") => 7

    }

  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("FP", "TAIS Track and Flight Plan") => "TAIS Track and Flight Plan"
    }
  }
}

case object table4 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("V", "Visual Flight Rules") => 1
      case joinTable("P", "Visual Flight Rules on Top") => 2
      case joinTable("E", "Enroute Instrument Flight Rules") => 3
      case joinTable("IFR", "Instrument Flight Rules") => 4
    }
  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("V", "Visual Flight Rules") => "Visual Flight Rules"
      case joinTable("P", "Visual Flight Rules on Top") => "Visual Flight Rules on Top"
      case joinTable("E", "Enroute Instrument Flight Rules") => "Enroute Instrument Flight Rules"
      case joinTable("IFR", "Instrument Flight Rules") => "Instrument Flight Rules"

    }
  }
}

case object table5 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("V", "Visual Flight Rules") => 1
      case joinTable("P", "Visual Flight Rules on Top") => 2
      case joinTable("E", "Enroute Instrument Flight Rules") => 3
      case joinTable("IFR", "Instrument Flight Rules") => 4
    }
  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("V", "Visual Flight Rules") => "Visual Flight Rules"
      case joinTable("P", "Visual Flight Rules on Top") => "Visual Flight Rules on Top"
      case joinTable("E", "Enroute Instrument Flight Rules") => "Enroute Instrument Flight Rules"
      case joinTable("IFR", "Instrument Flight Rules") => "Instrument Flight Rules"

    }
  }
}

case object table6 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("NW", "Northwest") => 2
      case joinTable("NW", "North") => 3
      case joinTable("NE", "Northeast") => 4
      case joinTable("W", "West") => 5
      case joinTable("E", "East") => 6
      case joinTable("SW", "Southwest") => 7
      case joinTable("S", "South") => 8
      case joinTable("SE", "Southeast") => 9
    }
  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("NW", "Northwest") => "Northwest"
      case joinTable("NW", "North") => "North"
      case joinTable("NE", "Northeast") => "Northeast"
      case joinTable("W", "West") => "West"
      case joinTable("E", "East") => "East"
      case joinTable("SW", "Southwest") => "Southwest"
      case joinTable("S", "South") => "South"
      case joinTable("SE", "Southeast") => "Southeast"
    }
  }
}

case object table7 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("B", "Heavy with RNAV") => 1
      case joinTable("F", "B757") => 2
      case joinTable("H", "Heavy") => 3
      case joinTable("J", "A380") => 4
      case joinTable("L", "B757 with RNAV") => 5
      case joinTable("M", "A380 with RNAV") => 6
      case joinTable("R", "RNAV") => 7
      case joinTable("X", "High Performance") => 8
    }
  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("B", "Heavy with RNAV") => None
      case joinTable("F", "B757") => None
      case joinTable("H", "Heavy") => "Heavy"
      case joinTable("J", "A380") => "A380"
      case joinTable("L", "B757 with RNAV") => "B757 with RNAV"
      case joinTable("M", "A380 with RNAV") => "A380 with RNAV"
      case joinTable("R", "RNAV") => "RNAV"
      case joinTable("X", "High Performance") => "High Performance "
    }
  }
}

case object table8 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("A", "Aircraft capable of MTOW of 300,000 pounds or more and a wingspan greater than 245 feet") => 1
      case joinTable("B", "Aircraft capable of MTOW of 300,000 pounds or more and a wingspan greater than 175 feet and less than or equal to 245 feet") => 2
      case joinTable("C", "Aircraft capable of a MTOW of 300,000 pounds or more and a wingspan greater than 125 feet and less than or equal to 175 feet") => 3
      case joinTable("D", "Aircraft capable of a MTOW less than 300,000 pounds and a wingspan greater than 125 feet and less than or equal to 175 feet; or, aircraft capable of a MTOW greater than 41,000 pounds with a wingspan greater than 90 feet and less than or equal to 125 feet") => 4
      case joinTable("E", "Aircraft capable of a MTOW greater than 41,000 pounds with a wingspan greater than 65 feet and less than or equal to 90 feet.") => 5
      case joinTable("F", "Aircraft capable of a MTOW of less than 41,000 pounds and a wingspan less than or equal to 125 feet, or aircraft capable of a MTOW less than 15,500 pounds regardless of wingspan, or a powered sailplane") => 6
    }
  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("A", "Aircraft capable of MTOW of 300,000 pounds or more and a wingspan greater than 245 feet") => "Aircraft + 300K lb and span > 245 ft"
      case joinTable("B", "Aircraft capable of MTOW of 300,000 pounds or more and a wingspan greater than 175 feet and less than or equal to 245 feet") => "Aircraft + 300K lb and span > 125 ft <=175 ft"
      case joinTable("C", "Aircraft capable of a MTOW of 300,000 pounds or more and a wingspan greater than 125 feet and less than or equal to 175 feet") => "Aircraft + 300K lb and span > 125 ft <=175 ft"
      case joinTable("D", "Aircraft capable of a MTOW less than 300,000 pounds and a wingspan greater than 125 feet and less than or equal to 175 feet; or, aircraft capable of a MTOW greater than 41,000 pounds with a wingspan greater than 90 feet and less than or equal to 125 feet") => "Aircraft>300K span>125<=175 or> 41K span>90<= 125"
      case joinTable("E", "Aircraft capable of a MTOW greater than 41,000 pounds with a wingspan greater than 65 feet and less than or equal to 90 feet.") => "Aircraft  > 41K lb and span > 65 ft or < = 90 ft"
      case joinTable("F", "Aircraft capable of a MTOW of less than 41,000 pounds and a wingspan less than or equal to 125 feet, or aircraft capable of a MTOW less than 15,500 pounds regardless of wingspan, or a powered sailplane") => ""
    }
  }
}

case object table9 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("Active", "Active Track") => 1
      case joinTable("Coasting", "Coasting Track") => 2
      case joinTable("Drop", "Dropped Track") => 3
    }
  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("Active", "Active Track") => "Active Track"
      case joinTable("Coasting", "Coasting Track") => "Coasting Track"
      case joinTable("Drop", "Dropped Track") => "Dropped Track"
    }
  }
}

case object table10 {

  def gettable1Row(row: recordFAA) = {
    joinTable(row.col1, row.col2)
  }
  def getCode(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("OLD", "No Track Update (TU) messages received for pseudo FDB") => 1
      case joinTable("PR", "Resume flight progress blinking indicator - Departure flight was suspended and disassociated while in a beacon mismatch condition, and has now become active") => 2
      case joinTable("CX", "Handoff cancelled by receiving facility blinking indicator") => 3
      case joinTable("OR", "Out of range blinking indicator -  Track’s current range is greater than an adapted distance from the selected single sensor’s location (Single sensor mode only)") => 4
      case joinTable("FP", "ARSA Flight plan transfer failure blinking indicator") => 5
      case joinTable("IF", "Unsuccessful Interfacility message transfer blinking indicator") => 6
      case joinTable("HO", "Late handoff blinking indicator") => 7
      case joinTable("DM", "Interfacility coordination blinking indicator") => 8
      case joinTable("HL", "Hold indicator") => 9
      case joinTable("TEC", "Tower Enroute Control (ARSA) flight indicator") => 10

    }
  }

  def getDescription(row: recordFAA) = {

    gettable1Row(row) match {
      case joinTable("OLD", "No Track Update (TU) messages received for pseudo FDB") => "No Track Update (TU) messages received for pseudo FDB"
      case joinTable("PR", "Resume flight progress blinking indicator - Departure flight was suspended and disassociated while in a beacon mismatch condition, and has now become active") => "Departure flight was suspended and has now become active"
      case joinTable("CX", "Handoff cancelled by receiving facility blinking indicator") => "Handoff cancelled by receiving facility blinking ind"
      case joinTable("OR", "Out of range blinking indicator -  Track’s current range is greater than an adapted distance from the selected single sensor’s location (Single sensor mode only)") => "Track’s range > than distance from sensor’s location"
      case joinTable("FP", "ARSA Flight plan transfer failure blinking indicator") => "ARSA Flight plan transfer failure blinking ind"
      case joinTable("IF", "Unsuccessful Interfacility message transfer blinking indicator") => "Unsuccessful Interfacility message transfer blinking ind"
      case joinTable("HO", "Late handoff blinking indicator") => "Late handoff blinking ind"
      case joinTable("DM", "Interfacility coordination blinking indicator") => "Interfacility coordination blinking ind"
      case joinTable("HL", "Hold indicator") => "Hold indicator"
      case joinTable("TEC", "Tower Enroute Control (ARSA) flight indicator") => "Tower Enroute Control (ARSA) flight ind"

    }
  }
}