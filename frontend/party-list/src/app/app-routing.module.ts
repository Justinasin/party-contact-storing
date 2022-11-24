import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PartyListComponent } from './party-list-compoment/party-list.component';

const routes: Routes = [
  {path: 'parties', component: PartyListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
