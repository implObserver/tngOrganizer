#!/bin/bash

if [ -z "$1" ]; then
  echo "❌ Укажи путь и имя модуля: ./create_fsd.sh <path>/<module>"
  exit 1
fi

# ============= 📦 Параметры =============
INPUT_PATH=$1
MODULE_NAME=$(basename "$INPUT_PATH")
MODULE_CAPITALIZED="$(tr '[:lower:]' '[:upper:]' <<< ${MODULE_NAME:0:1})${MODULE_NAME:1}"

TARGET_DIR="./$INPUT_PATH"
UI_DIR="$TARGET_DIR/ui"

# ============= 🧠 Определение package-пути =============
# Находим подстроку начиная с com/... (чтобы не включать src, java и т.д.)
PACKAGE_PATH=$(echo "$INPUT_PATH" | sed -n 's/.*\(com\/.*\)/\1/p' | tr '/' '.')

if [ -z "$PACKAGE_PATH" ]; then
  echo "❌ Не удалось определить package. Убедись, что путь содержит 'com/'"
  exit 1
fi

# ============= 📁 Создание директорий =============
mkdir -p "$UI_DIR"

# ============= 🧩 UI-компонент =============
cat > "$UI_DIR/${MODULE_CAPITALIZED}.kt" << EOF
package $PACKAGE_PATH.ui

import androidx.compose.runtime.Composable

@Composable
fun $MODULE_CAPITALIZED() {
    // TODO: UI here
}
EOF

# ============= 📍 Index-файл =============
cat > "$TARGET_DIR/index.kt" << EOF
package $PACKAGE_PATH

import $PACKAGE_PATH.ui.$MODULE_CAPITALIZED

EOF

echo "✅ Модуль '$MODULE_NAME' создан в: $TARGET_DIR"


