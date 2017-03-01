package todomanager.model;


/**
 * Created by butkoav on 27.02.2017.
 */
public class View {
    private int firstId;
    private int lastId;
    private int currId;
    private int rowsOnPage;
    private int rows;
    private int rowsBefore;
    private int currentPage;
    private int pages = 1;
    private int[] statuses;


    public View() {
        this.firstId = 1;
        this.rowsOnPage = 10;
        this.currentPage = 1;
        this.statuses = null;
    }


    public void calcPagesCount(boolean byStartId) {
        if (rowsOnPage == 0) {
            this.pages = 1;
        } else {
            this.pages = this.rows / (this.rowsOnPage);
            if (this.rows % this.rowsOnPage > 0)
                this.pages++;
        }
        currentPage = rowsBefore / (rowsOnPage);
        if (byStartId)
            currentPage++;
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public int getCurrId() {
        return currId;
    }

    public void setCurrId(int currId) {
        this.currId = currId;
    }

    public int getRowsBefore() {
        return rowsBefore;
    }

    public void setRowsBefore(int rowsBefore) {
        this.rowsBefore = rowsBefore;
    }

    public void setStatuses(int[] statuses) {
        this.statuses = statuses;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int[] getStatuses() {
        return statuses;
    }

    public int getFirstId() {
        return firstId;
    }

    public void setFirstId(int firstId) {
        this.firstId = firstId;
    }

    public int getRowsOnPage() {
        return rowsOnPage;
    }

    public void setRowsOnPage(int rowsOnPage) {
        this.rowsOnPage = rowsOnPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

}
