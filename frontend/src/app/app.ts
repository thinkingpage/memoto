import {Component, inject, Inject, OnInit, signal} from '@angular/core';

import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {FilterComponent} from './components/filters/filter.component';
import {MemoService} from './memo.service';
import {MemoModel} from './models/memo.model';
import {HttpErrorResponse} from '@angular/common/http';
import {MemoItemComponent} from './components/memos/memo-item.component';
import {MemoAddMemo} from './components/memos/memo-add-memo';
import {MemoAllMemosComponent} from './components/memos/memo-all-memos.component';
import {UserProfileService} from './keycloak/user-profile.service';
import {MenuComponent} from './components/menu.component';
import {KeycloakService} from './keycloak/keycloak.service';


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
  // keycloakservice = inject(KeycloakService);

  showAddMemoComponent = signal<boolean>(false);
  showMemoById = signal<boolean>(false);
  showAllMemoComponent = signal<boolean>(false);
  memos = signal<MemoModel[]>([]);
  memo = signal<MemoModel | null>(null);
  userProfileService = Inject(UserProfileService);

  constructor(private memoService: MemoService) {}

  ngOnInit() {
    this.loadMemos();
  }

  loadMemos() {
    this.memoService.getMemos().subscribe({
      next: (response: MemoModel[]) => {
        this.memos.set(response);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    });
  }

  activateAddForm() {
    this.showAddMemoComponent.update(value => !value);
  }

  activateMemoList() {
    this.showAllMemoComponent.update(value => !value);
    if(!this.showAllMemoComponent()) {
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
          console.log(error.message);
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
      error: (error: HttpErrorResponse) => console.log(error.message),
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
