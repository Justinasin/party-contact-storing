import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Party } from './party';

import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class PartyService {


  private headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Credentials': 'true',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
      'Authorization': 'Basic ' + btoa('admin:admin')
    })

  private baseUrl = "http://localhost:8080/parties";

  constructor(private http : HttpClient) { }

  getPartyList(): Observable<Party[]>{
    return this.http.get<Party[]>(`${this.baseUrl}`, { headers: this.headers}).pipe(map(response => response['data']));;
  }
}
