import {Component, OnInit} from '@angular/core';
import {Party} from '../party';
import {PartyService} from '../party.service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-party-list',
  templateUrl: './party-list.component.html',
  styleUrls: ['./party-list.component.scss']
})
export class PartyListComponent implements OnInit {

  parties: Party[] = [];
  party = new Party();

  constructor(private partyService: PartyService) {
  }

  ngOnInit(): void {
    this.getParties();
  }

  form = new FormGroup({
    firstName: new FormControl()
  });

  private getParties() {
    this.partyService.getPartyList().subscribe(data => {
      this.parties = data;
    });
  }

  private getPartyByFirstName(firstName: string) {
    this.partyService.getPartyByFirstName(firstName).subscribe(data => {
      this.party = data;
    });
  }

  searchForm() {
    let firstName = this.form.get('firstName').value;
    this.getPartyByFirstName(firstName);
  }
}
