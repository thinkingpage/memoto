import {Component, OnInit, signal} from '@angular/core';

import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {FilterComponent} from './components/filters/filter.component';
import {MemoService} from './memo.service';
import {MemoModel} from './models/memo.model';
import {HttpErrorResponse} from '@angular/common/http';
import {MemoItemComponent} from './components/memos/memo-item.component';
import {MemoAddMemo} from './components/memos/memo-add-memo';
import {MemoAllMemosComponent} from './components/memos/memo-all-memos.component';
import {MenuComponent} from './components/menu.component';
import {MemoDTO} from './models/memo.dto.model';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    HeaderComponent,
    MenuComponent,
    FilterComponent,
    MemoAllMemosComponent,
    FooterComponent,
    MemoAddMemo,
    MemoItemComponent,
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App implements OnInit {
  protected readonly title = signal('memoto');
  showAddMemoComponent = signal<boolean>(false);
  showMemoById = signal<boolean>(false);
  showAllMemoComponent = signal<boolean>(false);
  memos = signal<MemoModel[]>([]);
  memosDto = signal<MemoDTO[]>([]);
  memo = signal<MemoModel | null>(null);
  errorMessage = signal<string | null>(null);
  userColor = signal<string>('#ece3ca');

  constructor(private memoService: MemoService) {
  }

  ngOnInit() {
    this.loadMemos();
  }

  loadMemos() {
    this.memoService.getMemosDTO().subscribe({
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
      },
      next: (response: MemoDTO[]) => {
        this.memosDto.set(response);
      }
    })
  }

  get svgBackground() {
    return this.getNoiseSVG(this.userColor());
  }

  getNoiseSVG(color: string): string {
    const svg = `
      <svg width="650" height="500" xmlns="http://www.w3.org/2000/svg">
        <filter id='roughpaper' x='0%' y='0%' width='100%' height="100%">
          <feTurbulence type="fractalNoise" baseFrequency='1' result='noise' numOctaves="5" />
          <feDiffuseLighting in='noise' lighting-color='${color}' surfaceScale='2'>
              <feDistantLight azimuth='45' elevation='60' />
          </feDiffuseLighting>
        </filter>
        <rect x="0" y="0" width="100%" height="100%" filter="url(#roughpaper)" fill="none"/>
      </svg>
    `;
    return `url('data:image/svg+xml;utf8,${encodeURIComponent(svg)}')`;
  }

  activateAddForm() {
    this.showAddMemoComponent.update(value => !value);
  }

  activateMemoList() {
    this.showAllMemoComponent.update(value => !value);
    if (!this.showAllMemoComponent()) {
      return;
    }
  }

  addMemo(memo: MemoModel) {

    this.memoService.addMemo(memo).subscribe({
        next: (response: MemoModel) => {
          this.memos.update(value => {
              return [...value, response];
            }
          )
        },
        error: (error: HttpErrorResponse) => {
          console.log("errormessage: " + error.message);
          this.errorMessage.set(error.message);
          return error;
        },
      }
    )
  }

  memoDeleteById(id: number): void {

    this.memoService.deleteMemo(id).subscribe({
      next: () => {
        this.memos.update(value => {
            return value.filter(memo => memo.id !== id);
          }
        )
      },
      error: (error: HttpErrorResponse) => {
        console.log("errormessage: " + error.message);
        this.errorMessage.set(error.message);
        return error;
      },
    });
  }

  memoById(id: number): void {
    this.memoService.getMemo(id).subscribe({
      next: (memo: MemoModel) => {
        this.memo.set(memo);
      },
      error: (error: HttpErrorResponse) => console.log(error.message)
    })
  }
}
