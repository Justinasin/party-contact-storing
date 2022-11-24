import {Component, OnInit} from '@angular/core';
import {Party} from '../party';
import {PartyService} from '../party.service';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-party-list',
  templateUrl: './party-list.component.html',
  styleUrls: ['./party-list.component.scss']
})
export class PartyListComponent implements OnInit {

  parties: Party[] = [];

  constructor(private partyService: PartyService,
              private router: Router) { }

  ngOnInit(): void {
    this.getParties();
  }

  private getParties(){
    this.partyService.getPartyList().subscribe(data => {
      this.parties = data;
    });
  }

  form = new FormGroup({
    name: new FormControl(),
    email: new FormControl()
  });
}
