import { Injectable } from '@angular/core';

// @ts-ignore
import timezones from 'google-timezones-json';
import { ObjectValidationResponse, TenantResponse } from "@/app/core/model/api/api";

export interface Timezone {
  id: number,
  name: string,
  offset: string,
  description: string
}


@Injectable({
  providedIn: 'root'
})
export class TimezonesService {

  private _timeZoneList: Timezone[]
  private _timeZoneNameList: string[]

  constructor() {
    this._timeZoneList = []
    this._timeZoneNameList = []

    Object.entries(timezones)
      .forEach( (data: any) => {

        let offset = ""
        let regexSearch = /\(GMT([^\) ]+)\)/.exec(data[1])

        if (regexSearch) {
          offset = regexSearch[1]
        }

        this._timeZoneList.push(
          {
            id: this._timeZoneList.length,
            name: data[0].replace("_", "-"),
            offset: offset,
            description: data[1]
          }
        )

        this._timeZoneNameList.push(data[0])
      })
  }

  isTimeZoneNameValid(timeZoneName: string): boolean {
    return !this._timeZoneNameList.includes(timeZoneName)
  }

  get timeZoneList(): Timezone[] {
    return this._timeZoneList;
  }

  getTimeZoneById(id: number): Timezone {
    return this._timeZoneList.filter(
      function (timezone: Timezone) {
        return timezone.id == id;
      }
    )[0]
  }
}
