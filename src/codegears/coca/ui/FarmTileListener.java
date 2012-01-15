package codegears.coca.ui;

import codegears.coca.data.Tile;

public interface FarmTileListener {

	public void onPurchaseRequest(Tile data);
	public void onBuildRequest(Tile data);
	public void onAddItemRequest(Tile data);
	public void onSupplyRequest(Tile data);
	public void onHarvestRequest(Tile data);
	public void onMoveRequest(Tile data);
}
