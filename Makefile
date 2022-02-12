CC := $(CXX)
CPPFLAGS := -I ./library/SFML-2.5.1/include
LDFLAGS := -L ./library/SFML-2.5.1/lib -lsfml-graphics -lsfml-window -lsfml-system

SRC_DIR := ./src/
BUILD_DIR := ./build
BIN_DIR := $(BUILD_DIR)/bin/
OBJ_DIR := $(BUILD_DIR)/object

clean_object:
	del /s /q .\build\object\*.o

clean_all:
	del /s /q .\build\**\

copy_dependencies:
	copy .\library\SFML-2.5.1\bin\sfml-graphics-2.dll .\build\bin\sfml-graphics-2.dll
	copy .\library\SFML-2.5.1\bin\sfml-system-2.dll .\build\bin\sfml-system-2.dll
	copy .\library\SFML-2.5.1\bin\sfml-window-2.dll .\build\bin\sfml-window-2.dll

renderer.o: ./src/renderer/renderer.cpp
	$(CC) $(CPPFLAGS) -c $< -o $(OBJ_DIR)/$@

board.o: ./src/chess/board/board.cpp
	$(CC) $(CPPFLAGS) -c $< -o $(OBJ_DIR)/$@

application.o: ./src/application/application.cpp
	$(CC) $(CPPFLAGS) -c $< -o $(OBJ_DIR)/$@

app.exe: renderer.o board.o application.o
	$(eval OBJ_LOC := $(addprefix $(OBJ_DIR)/, $^))
	$(CC) -L ./library/SFML-2.5.1/lib -o $(BIN_DIR)/application.exe $(OBJ_LOC) -lsfml-graphics -lsfml-window -lsfml-system

build: copy_dependencies app.exe

fresh_build: clean_all build