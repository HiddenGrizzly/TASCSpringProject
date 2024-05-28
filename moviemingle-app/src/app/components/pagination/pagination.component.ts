import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PageReq } from 'src/app/models/pages/PageReq';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit, OnChanges {

  @Input() pageRes!: any;

  @Input() route!: any;

  pageReq: PageReq = {
    page: 0,
    size: 10
  };

  sizeOptions: number[] = [1, 5, 10, 15];

  pageNrs: number[] = [];

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes) {
      this.pageNrs = Array(this.pageRes.totalPages).fill(null).map((_, i) => i);
    }
  }


  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      console.log(params);
      if (params['page'] && params['size']) {
        this.pageReq = {
          page: Number(params['page']),
          size: Number(params['size'])
        }
      }
    })
  }

  toPage(pageNr: number): void {
    this.pageReq = { ...this.pageReq, page: pageNr };
    this.sendRequest();
  }

  setSize(pageSize: number): void {
    this.pageReq = { ...this.pageReq, size: pageSize };
    this.sendRequest();
  }

  sendRequest(): void {
    this.router.navigate([this.route], { queryParams: this.pageReq });
  }

}
