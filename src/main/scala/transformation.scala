
package com.honeywell.faa.transformation

import java.sql.Date

/*
case class SourceCaseClass (  timestamp : datetime ,  srcTracon : String ,  tracon : String ,  msgType : Int ,  version : String ,  srcVersion : String ,  sendTo : String ,  sfpn : Int ,  ocr : Int ,  rnav : Boolean ,  scratchpad1 : String ,  scratchpad2 : String ,  cps : String ,  runway : String ,  assignedBeaconCode : String ,  requestedAltitude : Int ,  category : Int ,  dbi : Int ,  acid : String ,  acAddress : String ,  acType : String ,  entryFix : String ,  exitFix : String ,  airport : String ,  flightRules : Int ,  type : Int ,  pdtTime : String ,  FlightPlanstatus : Int ,  delete : Boolean ,  suspended : Boolean ,  lld : Int ,  ECID : String ,  trackNum : Int ,  mrtTimestamp : TIMMESTAMP ,  status : Int ,  xPos : Int ,  yPos : Int ,  lat : Double ,  lon : Double ,  vVert : Int ,  vx : Int ,  vy : Int ,  frozen : Boolean ,  new : Boolean ,  pseudo : Boolean ,  adsb : Boolean ,  reportedBeaconCode : String ,  reportedAltitude : Int  )


case class targetCaseClass (  msg_gnrt_ts : TIMMESTAMP ,  src_tracon_id : String ,  tracon_id : String ,  msg_typ_cd : Int ,  msg_typ_desc : String ,  msg_typ_src_txt : String ,  vrsn_num : String ,  src_vrsn_num : String ,  auth_cd : String ,  sys_flgt_plan_indx_num : Int ,  flgt_plan_handoff_rsn_cd : Int ,  flgt_plan_handoff_rsn_desc : String ,  flgt_plan_handoff_rsn_src_txt : String ,  area_navg_ind : Boolean ,  atch_scrchpd_1_txt : String ,  atch_scrchpd_2_txt : String ,  cntlr_pstn_id : String ,  rnwy_id : String ,  asgn_trnspdr_sqwk_code : String ,  rqst_altd_feet_msr : Int ,  ac_cat_cd : Int ,  ac_cat_desc : String ,  ac_cat_src_txt : String ,  ac_wake_turblc_cat_cd : Int ,  ac_wake_turblc_cat_desc : String ,  ac_wake_turblc_cat_src_txt : String ,  actv_data_blk_faa_cd : Int ,  actv_data_blk_faa_desc : String ,  actv_data_blk_faa_src_txt : String ,  ac_src_cd_txt : String ,  ac_icao_addr : String ,  ac_typ_icao_code : String ,  ac_typ_iata_code : String ,  entr_fix_id : String ,  exit_fix_id : String ,  orig_arprt_iata_code : String ,  orig_arprt_icao_code : String ,  flgt_rul_cd : Int ,  flgt_rul_desc : String ,  flgt_rul_src_txt : String ,  flgt_oper_typ_cd : Int ,  flgt_oper_typ_desc : String ,  flgt_oper_typ_src_txt : String ,  plan_dprt_coor_tm : String ,  flgt_plan_stat_typ_cd : Int ,  flgt_plan_stat_typ_desc : String ,  flgt_plan_stat_typ_src_txt : String ,  flgt_plan_del_ind : Boolean ,  flgt_plan_sspd_ind : Boolean ,  ldr_line_dir_typ_id : Int ,  ldr_line_dir_typ_desc : String ,  ldr_line_dir_typ_src_txt : String ,  enrt_cmptr_id : String ,  sys_track_id : Int ,  mlti_radr_trckr_utc_ts : TIMMESTAMP ,  mlti_radr_trckr_utc_dt : Date ,  mlti_radr_trckr_utc_tm : TIMMESTAMP ,  track_stat_typ_cd : Int ,  track_stat_typ_desc : String ,  track_stat_typ_src_txt : String ,  ntcl_mile_sys_plne_x_pstn_msr : Int ,  ntcl_mile_sys_plne_y_pstn_msr : Int ,  lat_msr : Double ,  lng_msr : Double ,  vrtcl_vlcty_smth_msr : Int ,  kn_x_vlcty_smth_msr : Int ,  kn_y_vlcty_smth_msr : Int ,  track_sub_stat_ind : Boolean ,  new_track_stat_ind : Boolean ,  pseud_usupp_valu_ind : Boolean ,  ads_b_track_pstn_ind : Boolean ,  rpt_trnspdr_sqwk_code : String ,  rpt_altd_brmtrc_prsr_num : Int  )


trait transformationRules  {


  def timestampTomsg_gnrt_ts(): TIMMESTAMP = {
    """
    Source Column Name: timestamp
    Target Column Name: msg_gnrt_ts
    Transformation Rule: Set target to content of source field "timestamp"

    Note: The timestamp is in UTC format.

    """


  }


  def srcTraconTosrc_tracon_id(): String = {
    """
    Source Column Name: srcTracon
    Target Column Name: src_tracon_id
    Transformation Rule: Set target to content of source field "srcTracon"

    """


  }


  def traconTotracon_id(): String = {
    """
    Source Column Name: tracon
    Target Column Name: tracon_id
    Transformation Rule: Set target to content of source field " tracon"

    """


  }


  def msgTypeTomsg_typ_cd(): Int = {
    """
    Source Column Name: msgType
    Target Column Name: msg_typ_cd
    Transformation Rule: If source field is present
       Search Conversion Table 03 [Flight Message Source] where incoming value is equal to Source
       If row found
          Set target to Target Code
       End If
    End If

    """


  }


  def msgTypeTomsg_typ_desc(): String = {
    """
Source Column Name: msgType
Target Column Name: msg_typ_desc
Transformation Rule: If source field is present
   Search Conversion Table 03 [Flight Message Source] where incoming value is equal to Source
   If row found
      Set target to Target  Description
   End If
End If

"""


  }


  def msgTypeTomsg_typ_src_txt(): String = {
    """
Source Column Name: msgType
Target Column Name: msg_typ_src_txt
Transformation Rule: Set target to content of source field " msgType"

"""


  }


  def versionTovrsn_num(): String = {
    """
Source Column Name: version
Target Column Name: vrsn_num
Transformation Rule: Set target to content of source field "version"

Note: format is n.n

"""


  }


  def srcVersionTosrc_vrsn_num(): String = {
    """
Source Column Name: srcVersion
Target Column Name: src_vrsn_num
Transformation Rule: Set target to content of source field "srcVersion"

"""


  }


  def sendToToauth_cd(): String = {
    """
Source Column Name: sendTo
Target Column Name: auth_cd
Transformation Rule: Set target to content of source field "sendTo"

"""


  }


  def sfpnTosys_flgt_plan_indx_num(): Int = {
    """
Source Column Name: sfpn
Target Column Name: sys_flgt_plan_indx_num
Transformation Rule: Set target to content of source field "sfpn"

"""


  }


  def ocrToflgt_plan_handoff_rsn_cd(): Int = {
    """
Source Column Name: ocr
Target Column Name: flgt_plan_handoff_rsn_cd
Transformation Rule: If source field is present
   Search Conversion Table 05 [Flight Plan Handoff Type] where incoming value is equal to Source
   If row found
      Set target to Target Code
   End If
End If

"""


  }


  def ocrToflgt_plan_handoff_rsn_desc(): String = {
    """
Source Column Name: ocr
Target Column Name: flgt_plan_handoff_rsn_desc
Transformation Rule: If source field is present
   Search Conversion Table 05 [Flight Plan Handoff Type] where incoming value is equal to Source
   If row found
      Set target to Target Description
   End If
End If

"""


  }


  def ocrToflgt_plan_handoff_rsn_src_txt(): String = {
    """
Source Column Name: ocr
Target Column Name: flgt_plan_handoff_rsn_src_txt
Transformation Rule: Set target to content of source field "ocr"

"""


  }


  def rnavToarea_navg_ind(): Boolean = {
    """
Source Column Name: rnav
Target Column Name: area_navg_ind
Transformation Rule: Set target to content of source field "rnav"

"""


  }


  def scratchpad1Toatch_scrchpd_1_txt(): String = {
    """
Source Column Name: scratchpad1
Target Column Name: atch_scrchpd_1_txt
Transformation Rule: Set target to content of source field "scratchpad1"

"""


  }


  def scratchpad2Toatch_scrchpd_2_txt(): String = {
    """
Source Column Name: scratchpad2
Target Column Name: atch_scrchpd_2_txt
Transformation Rule: Set target to content of source field "scratchpad1"

"""


  }


  def cpsTocntlr_pstn_id(): String = {
    """
Source Column Name: cps
Target Column Name: cntlr_pstn_id
Transformation Rule: Set target to content of source field "cps"

"""


  }


  def runwayTornwy_id(): String = {
    """
Source Column Name: runway
Target Column Name: rnwy_id
Transformation Rule: Set target to content of source field "runway"

"""


  }


  def assignedBeaconCodeToasgn_trnspdr_sqwk_code(): String = {
    """
Source Column Name: assignedBeaconCode
Target Column Name: asgn_trnspdr_sqwk_code
Transformation Rule: Set target to content of source field "assignedBeaconCode"

"""


  }


  def requestedAltitudeTorqst_altd_feet_msr(): Int = {
    """
Source Column Name: requestedAltitude
Target Column Name: rqst_altd_feet_msr
Transformation Rule: Set target to content of source field "requestedAltitude"

"""


  }


  def categoryToac_cat_cd(): Int = {
    """
Source Column Name: category
Target Column Name: ac_cat_cd
Transformation Rule: If source field is present
   Search Conversion Table 07 [Aircraft Category] where incoming value is equal to Source
   If row found
      Set target to Target Code
   End If
End If

Note: When source field contains "B" or "F", the conversion table does not contain a mapping.  This is because FAA has not provided Honeywell a viable way of determining whether this is Aircraft Category data or Wake Turbulence data.  Hence we are loosing this data.

"""


  }


  def categoryToac_cat_desc(): String = {
    """
Source Column Name: category
Target Column Name: ac_cat_desc
Transformation Rule: If source field is present
   Search Conversion Table 07 [Aircraft Category] where incoming value is equal to Source
   If row found
      Set target to Target Description
   End If
End If

Note: When source field contains "B" or "F", the conversion table does not contain a mapping.  This is because FAA has not provided Honeywell a viable way of determining whether this is Aircraft Category data or Wake Turbulence data.  Hence we are loosing this data.

"""


  }


  def categoryToac_cat_src_txt(): String = {
    """
Source Column Name: category
Target Column Name: ac_cat_src_txt
Transformation Rule: If source field is present
   Search Conversion Table 07 [Aircraft Category] where incoming value is equal to Source
   If row found
      Set target to content of source field "category"
   End If
End If

Note: When source field contains "B" or "F", the conversion table does not contain a mapping.  This is because FAA has not provided Honeywell a viable way of determining whether this is Aircraft Category data or Wake Turbulence data.  Hence we are loosing this data.

"""


  }


  def categoryToac_wake_turblc_cat_cd(): Int = {
    """
Source Column Name: category
Target Column Name: ac_wake_turblc_cat_cd
Transformation Rule: If source field is present
   Search Conversion Table 08 [Aircraft Wake Turbulence] where incoming value is equal to Source
   If row found
      Set target to Target Code
   End If
End If

Note: When source field contains "B" or "F", the conversion table does not contain a mapping.  This is because FAA has not provided Honeywell a viable way of determining whether this is Aircraft Category data or Wake Turbulence data.  Hence we are loosing this data.

"""


  }


  def categoryToac_wake_turblc_cat_desc(): String = {
    """
Source Column Name: category
Target Column Name: ac_wake_turblc_cat_desc
Transformation Rule: If source field is present
   Search Conversion Table 08 [Aircraft Wake Turbulence] where incoming value is equal to Source
   If row found
      Set target to Target Description
   End If
End If

Note: When source field contains "B" or "F", the conversion table does not contain a mapping.  This is because FAA has not provided Honeywell a viable way of determining whether this is Aircraft Category data or Wake Turbulence data.  Hence we are loosing this data.

"""


  }


  def categoryToac_wake_turblc_cat_src_txt(): String = {
    """
Source Column Name: category
Target Column Name: ac_wake_turblc_cat_src_txt
Transformation Rule: If source field is present
   Search Conversion Table 08 [[Aircraft Wake Turbulence] where incoming value is equal to Source
   If row found
      Set target to content of source field "category"
   End If
End If

Note: When source field contains "B" or "F", the conversion table does not contain a mapping.  This is because FAA has not provided Honeywell a viable way of determining whether this is Aircraft Category data or Wake Turbulence data.  Hence we are loosing this data.

"""


  }


  def dbiToactv_data_blk_faa_cd(): Int = {
    """
Source Column Name: dbi
Target Column Name: actv_data_blk_faa_cd
Transformation Rule: If source field is present
   Search Conversion Table 10 [Data Block] where incoming value is equal to Source
   If row found
      Set target to Target Code
   End If
End If

"""


  }


  def dbiToactv_data_blk_faa_desc(): String = {
    """
Source Column Name: dbi
Target Column Name: actv_data_blk_faa_desc
Transformation Rule: If source field is present
   Search Conversion Table 10 [Data Block] where incoming value is equal to Source
   If row found
      Set target to Target Description
   End If
End If

"""


  }


  def dbiToactv_data_blk_faa_src_txt(): String = {
    """
Source Column Name: dbi
Target Column Name: actv_data_blk_faa_src_txt
Transformation Rule: Set target to content of source field "dbi"

"""


  }


  def acidToac_src_cd_txt(): String = {
    """
Source Column Name: acid
Target Column Name: ac_src_cd_txt
Transformation Rule: Set target to content of source field "acid"

"""


  }


  def acAddressToac_icao_addr(): String = {
    """
Source Column Name: acAddress
Target Column Name: ac_icao_addr
Transformation Rule: Set target to content of source field "acAddress"

"""


  }


  def acTypeToac_typ_icao_code(): String = {
    """
Source Column Name: acType
Target Column Name: ac_typ_icao_code
Transformation Rule: Set target to content of source field "AcType"

"""


  }


  def acTypeToac_typ_iata_code(): String = {
    """
Source Column Name: acType
Target Column Name: ac_typ_iata_code
Transformation Rule: If source field is present
   Search Aircraft reference data for row where ARCFT_ICAO_CODE is equal to "acType"
   If row found
      Set target to content of ARCRFT.ARCFT_IATA_CODE
   End If
End If

"""


  }


  def entryFixToentr_fix_id(): String = {
    """
Source Column Name: entryFix
Target Column Name: entr_fix_id
Transformation Rule: Set target to content of source field "entryFix"

"""


  }


  def exitFixToexit_fix_id(): String = {
    """
Source Column Name: exitFix
Target Column Name: exit_fix_id
Transformation Rule: Set target to content of source field "exitFix"

"""


  }


  def airportToorig_arprt_iata_code(): String = {
    """
Source Column Name: airport
Target Column Name: orig_arprt_iata_code
Transformation Rule: If source field is present
   Search Airport reference data for row where ARPRT_ICAO_CODE is equal to "acType"
   If row found
      Set target to content of ARPRT.ARPRT_IATA_CODE
   End If
End If

"""


  }


  def airportToorig_arprt_icao_code(): String = {
    """
Source Column Name: airport
Target Column Name: orig_arprt_icao_code
Transformation Rule: Set target to content of source field "airport"


"""


  }


  def flightRulesToflgt_rul_cd(): Int = {
    """
Source Column Name: flightRules
Target Column Name: flgt_rul_cd
Transformation Rule: If source field is present
   Search Conversion Table 04 [Flight Rules Type] where incoming value is equal to Source
   If row found
      Set target to Target Code
   End If
End If

"""


  }


  def flightRulesToflgt_rul_desc(): String = {
    """
Source Column Name: flightRules
Target Column Name: flgt_rul_desc
Transformation Rule: If source field is present
   Search Conversion Table 04 [Flight Rules Type] where incoming value is equal to Source
   If row found
      Set target to Target Description
   End If
End If

"""


  }


  def flightRulesToflgt_rul_src_txt(): String = {
    """
Source Column Name: flightRules
Target Column Name: flgt_rul_src_txt
Transformation Rule: Set target to content of source field "flightRules"

"""


  }


  def typeToflgt_oper_typ_cd(): Int = {
    """
Source Column Name: type
Target Column Name: flgt_oper_typ_cd
Transformation Rule: If source field is present
   Search Conversion Table 02 [Flight Operation Type] where incoming value is equal to Source
   If row found
      Set target to Target Code
   End If
End If


"""


  }


  def typeToflgt_oper_typ_desc(): String = {
    """
Source Column Name: type
Target Column Name: flgt_oper_typ_desc
Transformation Rule: If source field is present
   Search Conversion Table 02 [Flight Operation Type] where incoming value is equal to Source
   If row found
      Set target to Target Description
   End If
End If


"""


  }


  def typeToflgt_oper_typ_src_txt(): String = {
    """
Source Column Name: type
Target Column Name: flgt_oper_typ_src_txt
Transformation Rule: Set target to content of source field "type"

"""


  }


  def pdtTimeToplan_dprt_coor_tm(): String = {
    """
Source Column Name: pdtTime
Target Column Name: plan_dprt_coor_tm
Transformation Rule: Set target to content of source field "pdtTime" (format HHMM)

"""


  }


  def FlightPlanstatusToflgt_plan_stat_typ_cd(): Int = {
    """
Source Column Name: FlightPlanstatus
Target Column Name: flgt_plan_stat_typ_cd
Transformation Rule: If source field is present
   Search Conversion Table 01 [Flight Status] where incoming value is equal to Source
   If row found
      Set target to Target Code
   End If
End If


"""


  }


  def FlightPlanstatusToflgt_plan_stat_typ_desc(): String = {
    """
Source Column Name: FlightPlanstatus
Target Column Name: flgt_plan_stat_typ_desc
Transformation Rule: If source field is present
   Search Conversion Table 01 [Flight Status] where incoming value is equal to Source
   If row found
      Set target to Target Description
   End If
End If

"""


  }


  def FlightPlanstatusToflgt_plan_stat_typ_src_txt(): String = {
    """
Source Column Name: FlightPlanstatus
Target Column Name: flgt_plan_stat_typ_src_txt
Transformation Rule: Set target to content of source field "FlightPlanstatus"

"""


  }


  def deleteToflgt_plan_del_ind(): Boolean = {
    """
Source Column Name: delete
Target Column Name: flgt_plan_del_ind
Transformation Rule: Set target to content of source field "delete"

"""


  }


  def suspendedToflgt_plan_sspd_ind(): Boolean = {
    """
Source Column Name: suspended
Target Column Name: flgt_plan_sspd_ind
Transformation Rule: Set target to content of source field "suspended"

"""


  }


  def lldToldr_line_dir_typ_id(): Int = {
    """
  Source Column Name: lld
  Target Column Name: ldr_line_dir_typ_id
  Transformation Rule: If source field is present
     Search Conversion Table 06 [Leader Line Direction Type] where incoming value is equal to Source
     If row found
        Set target to Target Code
     End If
  End If

  """


  }


  def lldToldr_line_dir_typ_desc(): String = {
    """
Source Column Name: lld
Target Column Name: ldr_line_dir_typ_desc
Transformation Rule: If source field is present
   Search Conversion Table 06 [Leader Line Direction Type] where incoming value is equal to Source
   If row found
      Set target to Target Description
   End If
End If

"""


  }


  def lldToldr_line_dir_typ_src_txt(): String = {
    """
Source Column Name: lld
Target Column Name: ldr_line_dir_typ_src_txt
Transformation Rule: Set target to content of source field "lld"

"""


  }


  def ECIDToenrt_cmptr_id(): String = {
    """
Source Column Name: ECID
Target Column Name: enrt_cmptr_id
Transformation Rule: Set target to content of source field "ECID"

"""


  }


  def trackNumTosys_track_id(): Int = {
    """
Source Column Name: trackNum
Target Column Name: sys_track_id
Transformation Rule: Set target to content of source field "trackNum"

"""


  }


  def mrtTimestampTomlti_radr_trckr_utc_ts(): TIMMESTAMP = {
    """
Source Column Name: mrtTimestamp
Target Column Name: mlti_radr_trckr_utc_ts
Transformation Rule: Set target to content of source field "mrtTimestamp"

Note: Format is yyyy-MM-dd HH:mm:ss.SSS

"""


  }


  def mrtTimestampTomlti_radr_trckr_utc_dt(): Date = {
    """
Source Column Name: mrtTimestamp
Target Column Name: mlti_radr_trckr_utc_dt
Transformation Rule: Set target  field "mrtTimestamp"   to yyyy-mm-dd from "yyyy-MM-dd HH:mm:ss.SSS" format

"""


  }


  def mrtTimestampTomlti_radr_trckr_utc_tm(): TIMMESTAMP = {
    """
Source Column Name: mrtTimestamp
Target Column Name: mlti_radr_trckr_utc_tm
Transformation Rule: Set target  field "mrtTimestamp"  to hh:mm:ss from  "yyyy-MM-dd HH:mm:ss.SSS" format

"""


  }


  def statusTotrack_stat_typ_cd(): Int = {
    """
Source Column Name: status
Target Column Name: track_stat_typ_cd
Transformation Rule: If source field is present
   Search Conversion Table 09 [Track Status] where incoming value is equal to Source
   If row found
      Set target to Target Code
   End If
End If

"""


  }


  def statusTotrack_stat_typ_desc(): String = {
    """
    Source Column Name: status
    Target Column Name: track_stat_typ_desc
    Transformation Rule: If source field is present
       Search Conversion Table 09 [Track Status] where incoming value is equal to Source
       If row found
          Set target to Target Description
       End If
    End If

    """


  }


  def statusTotrack_stat_typ_src_txt(): String = {
    """
Source Column Name: status
Target Column Name: track_stat_typ_src_txt
Transformation Rule: Set target to content of source field "status"

"""


  }


  def xPosTontcl_mile_sys_plne_x_pstn_msr(): Int = {
    """
Source Column Name: xPos
Target Column Name: ntcl_mile_sys_plne_x_pstn_msr
Transformation Rule: Set target to content of source field "xPos"

"""


  }


  def yPosTontcl_mile_sys_plne_y_pstn_msr(): Int = {
    """
Source Column Name: yPos
Target Column Name: ntcl_mile_sys_plne_y_pstn_msr
Transformation Rule: Set target to content of source field "yPos"

"""


  }


  def latTolat_msr(): Double = {
    """
Source Column Name: lat
Target Column Name: lat_msr
Transformation Rule: Set target to content of source field "lat"

"""


  }


  def lonTolng_msr(): Double = {
    """
Source Column Name: lon
Target Column Name: lng_msr
Transformation Rule: Set target to content of source field "lon"

"""


  }


  def vVertTovrtcl_vlcty_smth_msr(): Int = {
    """
Source Column Name: vVert
Target Column Name: vrtcl_vlcty_smth_msr
Transformation Rule: Set target to content of source field "vVert"

"""


  }


  def vxTokn_x_vlcty_smth_msr(): Int = {
    """
Source Column Name: vx
Target Column Name: kn_x_vlcty_smth_msr
Transformation Rule: Set target to content of source field "vx"

"""


  }


  def vyTokn_y_vlcty_smth_msr(): Int = {
    """
Source Column Name: vy
Target Column Name: kn_y_vlcty_smth_msr
Transformation Rule: Set target to content of source field "vy"

"""


  }


  def frozenTotrack_sub_stat_ind(): Boolean = {
    """
Source Column Name: frozen
Target Column Name: track_sub_stat_ind
Transformation Rule: Set target to content of source field "frozen"

"""


  }


  def newTonew_track_stat_ind(): Boolean = {
    """
    Source Column Name: new
    Target Column Name: new_track_stat_ind
    Transformation Rule: Set target to content of source field "new"

    """


  }


  def pseudoTopseud_usupp_valu_ind(): Boolean = {
    """
Source Column Name: pseudo
Target Column Name: pseud_usupp_valu_ind
Transformation Rule: Set target to content of source field "pseudo"

"""


  }


  def adsbToads_b_track_pstn_ind(): Boolean = {
    """
Source Column Name: adsb
Target Column Name: ads_b_track_pstn_ind
Transformation Rule: Set target to content of source field "adsb"

"""


  }


  def reportedBeaconCodeTorpt_trnspdr_sqwk_code(): String = {
    """
Source Column Name: reportedBeaconCode
Target Column Name: rpt_trnspdr_sqwk_code
Transformation Rule: Set target to content of source field "reportedBeaconCode"

"""


  }


  def reportedAltitudeTorpt_altd_brmtrc_prsr_num(): Int = {
    """
    Source Column Name: reportedAltitude
    Target Column Name: rpt_altd_brmtrc_prsr_num
    Transformation Rule: Set target to content of source field "reportedAltitude"

    """


  }
}

*/