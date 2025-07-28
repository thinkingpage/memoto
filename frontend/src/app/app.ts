import {ChangeDetectionStrategy, Component, OnInit, signal} from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {FilterComponent} from './components/filters/filter.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    HeaderComponent,
    FilterComponent,
    FooterComponent
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App implements OnInit {
  protected readonly title = signal('memoto');

  ngOnInit() {}
}
