import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-generic-error',
  templateUrl: './generic-error.component.html',
  styleUrls: ['./generic-error.component.scss']
})
export class GenericErrorComponent implements OnInit {

  error: HttpErrorResponse

  constructor(private route: ActivatedRoute) {
    this.error = new HttpErrorResponse({})
  }

  ngOnInit() {
    this.route.data
      .subscribe((data: any) => {
        this.error = data.error as HttpErrorResponse
        if(this.error.error){
          if (this.error.error.message){
            this.error.error.message =
              this.error.error.message.replace(/(\w)(\w*)/g,
                function(g0:any,g1:any,g2:any){return g1.toUpperCase() + g2.toLowerCase();});
          }
        }
      })
  }
}
