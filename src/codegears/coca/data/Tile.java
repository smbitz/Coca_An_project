package codegears.coca.data;

import org.w3c.dom.Node;

public class Tile {
	
	public static final int BUILDING_NOTOCCUPY = 0;
	public static final int BUILDING_EMPTY = 1;
	public static final int BUILDING_PROCESS1 = 2;
	public static final int BUILDING_PROCESS2 = 3;
	public static final int BUILDING_COMPLETED = 4;
	public static final int BUILDING_ROTTED = 5;
	
	public static final int SUPPY_FULL_PERCEN = 1;
	public static final int SUPPY_OUT_PERCEN = 0;
	
	private String landType;
	private Boolean isOccupy;
	private String buildingId;
	private int progress;
	private int supply;
	private String extraId;
	private int rottenPeriod;
	
	private Building building;
	
	public void setDataFromXmlNode(Node node){
		landType = node.getAttributes().getNamedItem("land_type").getNodeValue();
		isOccupy = Boolean.valueOf(node.getAttributes().getNamedItem("is_occupy").getNodeValue());
		buildingId = node.getAttributes().getNamedItem("building_id").getNodeValue();
		progress = Integer.parseInt(node.getAttributes().getNamedItem("progress").getNodeValue());
		supply = Integer.parseInt(node.getAttributes().getNamedItem("supply_left").getNodeValue());
		extraId = node.getAttributes().getNamedItem("extra_id").getNodeValue();
		rottenPeriod = Integer.parseInt(node.getAttributes().getNamedItem("rotten_period").getNodeValue());
	}
	
	public int getBuildingStatus(){
		if(!isOccupy){
			return BUILDING_NOTOCCUPY;
		}
		
		if(building!=null){
			if(progress>=(building.getBuildPeriod()*0.5)){
			  //if progress >= 0 - 50%
				return BUILDING_PROCESS1;
			}else if(progress<(building.getBuildPeriod()*0.5)&&progress>0){
			  //if progress > 50% AND < 100%
				return BUILDING_PROCESS2;
			}else if(progress<=0&&rottenPeriod>0){
			  //if progress >= 100% (but not rotted)
				return BUILDING_COMPLETED;
			}else{
			  //if rotten < 0
				return BUILDING_ROTTED;
			}
		}else{
			return BUILDING_EMPTY;
		}
	}
	
	public String getBuildingId(){
		return this.buildingId;
	}
	
	public String getLandType(){
		return this.landType;
	}
	
	public Boolean getIsCooupy(){
		return this.isOccupy;
	}
	
	public Building getBuilding(){
		return this.building;
	}
	
	public String getExtraId(){
		return this.extraId;
	}
	
	public void setBuilding(Building setValue){
		this.building = setValue;
	}
	
	public void setSupply(int setValue){
		this.supply = setValue;
	}
	
	public void clearTile() {
		this.buildingId = "NULL";
		this.progress = 0;
		this.supply = 0;
		this.extraId = "NULL";
		this.building = null;
		this.rottenPeriod = 0;
	}

	public int getSupplyPercentage() {
		int currentPercen;
		
		currentPercen = (SUPPY_FULL_PERCEN/building.getSupplyPeriod())*supply;
		
		if(currentPercen<SUPPY_OUT_PERCEN){
			currentPercen = SUPPY_OUT_PERCEN;
		}
		
		return currentPercen;
	}
	
}