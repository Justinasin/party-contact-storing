import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';

import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { PartyListComponent } from './party-list-compoment/party-list.component';

import { PartyService } from './party.service';

@NgModule({
  declarations: [
    AppComponent,
    PartyListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [PartyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
