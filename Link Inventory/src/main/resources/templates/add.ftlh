<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="/css/style.css">
  <link rel="preconnect" href="https://fonts.googleapis.com" crossorigin="anonymous">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@300;400;500;600;700&display=swap" rel="stylesheet" crossorigin="anonymous">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" crossorigin="anonymous"/>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" crossorigin="anonymous">
  <title>Добавление места</title>
  <script src="/js/addrow.js" crossorigin="anonymous"></script>
</head>

<body>
  <div class="web-container">
    <aside>
          <div class="aside__top">
            <div class="aside__logo">
              <img src="/images/logo.png" alt="logo">
              <h2 class="text-muted">
                <span class="text-border"><span class="color-primary">Link </span><span class="color-success">
                    Inv.</span></span>
              </h2>
            </div>
            <div class="aside__close" id="close-btn">
              <span class="material-symbols-sharp">
                close
              </span>
            </div>
          </div>
          <div class="aside__sidebar">
            <a href="/">
                      <span class="material-symbols-sharp">
                        grid_view
                      </span>
                      <h3>Главная страница</h3>
                    </a>
                    <#if user.isWorker()==false>
                    <a href="/add">
                      <span class="material-symbols-sharp">
                        add_circle
                      </span>
                      <h3>Новая задача</h3>
                    </a>
                                        </#if>
                                        <#if user.isAdmin()>
                    <a href="/admin">
                    <span class="material-symbols-sharp">
                    admin_panel_settings
                    </span>
                    <h3>Администрирование</h3>
                    </a>
                    </#if>
                    <a href="/about">
                      <span class="material-symbols-sharp">
                        info
                      </span>
                      <h3>О сайте</h3>
                    </a>
                                    <a>
                                      <span class="material-symbols-sharp">
                                        logout
                                      </span>
                                    <form action="/logout" method="post">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <h3><input type="submit" value="Выйти"/></h3>
                                    </form>
                                    </a>
          </div>
        </aside>

    <main>
            <div class="web__head">
              <div class="header__title"><h1>Добавление новой задачи</h1></div>
              <div class="right-side">
                <div class="top">
                  <button id="menu-btn">
                    <span class="material-symbols-sharp">
                      menu
                    </span>
                  </button>
                  <div class="profile">
                    <div class="info">
                      <p>${user.name}</p>
                      <small class="text-muted">${user.stringRole()}</small>
                    </div>
                    <div class="profile-photo">
                      <img src="/images/${user.stringRole()}.png" alt="photo">
                    </div>
                  </div>
                </div>
            </div>
            </div>
      <form action="/place/add" method="post">
      <div class="main__insights">
        <div class="main__input">
          <div class="input__add">
            <h2>Адресс</h2>
            <input type="text" name="title" class="input-name" placeholder="Введите название...">
          </div>
        </div>
         <div class="main__input">
                  <div class="input__add">
                    <h2>Организация</h2>
                    <input type="text" name="organization" class="input-name" placeholder="Введите название...">
                  </div>
                </div>
      </div>
      <div class="main__checklist">
        <h2>Регламентированный&nbsp;список&nbsp;для&nbsp;инвентаризации</h2>
        <table>
          <thead>
            <th>№</th>
            <th>Описание</th>
            <th>Серийный номер</th>
          </thead>
          <tbody id="tableBody">
            <tr>
              <td>1</td>
              <td><input name="1_0" type="text" class="input-td"></td>
              <td><input name="1_1" type="text" class="input-td"></td>
            </tr>
            <tr>
              <td>2</td>
              <td><input name="2_0" type="text" class="input-td"></td>
              <td><input name="2_1" type="text" class="input-td"></td>
            </tr>
            <tr>
              <td>3</td>
              <td><input name="3_0" type="text" class="input-td"></td>
              <td><input name="3_1" type="text" class="input-td"></td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td id="plus-btn-td" colspan="3">
                <button type="button" class="plus-btn" onclick="addRow()">
                  <span class="material-symbols-sharp">
                    add
                  </span>
                </button>
              </td>
            </tr>
          </tfoot>
        </table>
      </div>
      <div class="add-btn-style">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="add-btn">
          <h2>Добавить</h2>
        </button>
      </div>
      </form>
    </main>
  </div>
</body>
</html>