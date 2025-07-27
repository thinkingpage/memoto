import {ChangeDetectionStrategy, Component, OnInit, signal} from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {MemoModel} from './models/memo.model';
import {MemoService} from './memo.service';
import {HttpErrorResponse} from '@angular/common/http';
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
  public memos = signal<MemoModel[]>([]);
  // constructor(private memoService: MemoService) {}
  //
  ngOnInit() {

  }
  //
  // public getMemos(): void {
  //   this.memoService.getMemos().subscribe({
  //     next: (response: MemoModel[]) => {
  //       this.memos.set(response);
  //     },
  //     error: (error: HttpErrorResponse) => {
  //       console.log(error.message);
  //     }
  //   });
  // }
}
