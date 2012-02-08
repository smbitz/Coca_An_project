package codegears.coca.data;

import org.w3c.dom.Node;

import codegears.coca.MyApp;

public class Tile {
	
	public static final int BUILDING_NOTOCCUPY = 0;
	public static final int BUILDING_EMPTY = 1;
	public static final int BUILDING_PROCESS1 = 2;
	public static final int BUILDING_PROCESS2 = 3;
	public static final int BUILDING_COMPLETED = 4;
	public static final int BUILDING_ROTTED = 5;
	
	public static final int SUPPY_FULL_PERCEN = 1;
	public static final int SUPPY_OUT_PERCEN = 0;
	
	public static final String LAND_TYPE_LAND = "land";
	public static final String LAND_TYPE_SEA = "sea";
	
	public static final String BUILDING_TYPE_VEGE = "vege";
	public static final String BUILDING_TYPE_MEAT = "meat";
	public static final String BUILDING_TYPE_SEA = "sea";
	
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
		progress = Integer.parseInt(node.getAttributes().getNamedItem("progress").getNodeValue())*1000;
		supply = Integer.parseInt(node.getAttributes().getNamedItem("supply_left").getNodeValue())*1000;
		extraId = node.getAttributes().getNamedItem("extra_id").getNodeValue();
		rottenPeriod = Integer.parseInt(node.getAttributes().getNamedItem("rotten_period").getNodeValue())*1000;
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
	
	public Boolean getIsOccupy(){
		return this.isOccupy;
	}
	
	public Building getBuilding(){
		return this.building;
	}
	
	public String getExtraId(){
		return this.extraId;
	}
	
	public int getProgress(){
		return this.progress;
	}
	
	public int getSupply(){
		return this.supply;
	}
	
	public int getRottenPeriod(){
		return this.rottenPeriod;
	}
	
	public void setBuilding(Building setValue){
		this.building = setValue;
	}
	
	public void setSupply(int setValue){
		this.supply = setValue;
	}
	
	public void setIsOccupy(boolean setValue){
		this.isOccupy = setValue;
	}
	
	public void setExtraId(String setValue){
		this.extraId = setValue;
	}
	
	public void setBuildingId(String setValue){
		this.buildingId = setValue;
	}
	
	public void setProgress(int setValue){
		this.progress = setValue;
	}
	
	public void setRottenPeriod(int setValue){
		this.rottenPeriod = setValue;
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

	public boolean isAllowToBuild(Building building) {
		if(landType.equals(LAND_TYPE_LAND) && building.getBuildingType().equals(BUILDING_TYPE_VEGE) && isOccupy ||
		   landType.equals(LAND_TYPE_LAND) && building.getBuildingType().equals(BUILDING_TYPE_MEAT) && isOccupy ||
		   landType.equals(LAND_TYPE_SEA) && building.getBuildingType().equals(BUILDING_TYPE_SEA) && isOccupy){
			   return true;
		} else {
			   return false;
		}
	}
	
	public void buildTile(Building buildValue){
		this.buildingId = buildValue.getId();
		this.progress = buildValue.getBuildPeriod();
		this.supply = 0;
		this.extraId = "NULL";
		this.rottenPeriod = buildValue.getRottenPeriod();
		
		this.setBuilding(buildValue);
	}
	
	public void update(int elapse){
		if( building != null ){
			int buildingStatus = getBuildingStatus();
			if(buildingStatus == BUILDING_EMPTY){
				//update elapse with nothing
			} else if(buildingStatus == BUILDING_PROCESS1){
				//update elapse with progress
				if(this.supply>0){
					this.progress -= elapse;
					this.supply -= elapse;
				}
				buildingStatus = this.getBuildingStatus();
			} else if(buildingStatus == BUILDING_PROCESS2){
				//update elapse with progress
				if(this.supply>0){
					this.progress -= elapse;
					this.supply -= elapse;
				}
				buildingStatus = this.getBuildingStatus();
			} else if(buildingStatus == BUILDING_COMPLETED){
				//update elapse with rotten
				this.rottenPeriod -= elapse;
				buildingStatus = this.getBuildingStatus();
			} else if(buildingStatus == BUILDING_ROTTED){
				//update elapse with nothing
			}
		}
	}
	
}