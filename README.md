# ShiftAndF
可以讓玩家使用 Shift + F 或 Shift + Q 來執行指令

## 使用說明
將插件放入 plugins 後讀取插件或重開伺服器，即可產生 config.yml 並進入編輯。
將 enable_F 設定為 true 即可開啟 Shift + F 功能。 (Q 也是相同)
在 S-F_command 中設定想要的指令就可以了

## 插件指令與權限
| 指令 | 說明 | 權限 |
| ------ | ------ | ------ |
| saf | 查看作者及版本資訊 | x |
| saf reload | 重新讀取插件 | saf.reload (預設 op) |

## config.yml
```yml
prefix: "§e[SAF] §r"
enable_F: false
S-F_command:
- ''
enable_Q: false
S-Q_command:
- ''
```