export class PageInfo {
    currentPage!: number;
    totalItems!: number;
    totalPages!: number;

    constructor(response: any) {
        this.currentPage = response.currentPage;
        this.totalItems = response.totalItems;
        this.totalPages = response.totalPages;
    }

}
