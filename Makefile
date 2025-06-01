CC := $(CXX)
CPPFLAGS := -I ./library/SFML-2.5.1/include
LDFLAGS := -L ./library/SFML-2.5.1/lib -lsfml-graphics -lsfml-window -lsfml-system

SRC_DIR := ./src
BUILD_DIR := ./build
BIN_DIR := $(BUILD_DIR)/bin
OBJ_DIR := $(BUILD_DIR)/object

clean_object:
	del /s /q .\build\object\*.o

clean_all:
	del /s /q .\build\**\

copy_dependencies:
	copy .\library\SFML-2.5.1\bin\sfml-graphics-2.dll .\build\bin\sfml-graphics-2.dll
	copy .\library\SFML-2.5.1\bin\sfml-system-2.dll .\build\bin\sfml-system-2.dll
	copy .\library\SFML-2.5.1\bin\sfml-window-2.dll .\build\bin\sfml-window-2.dll

copy_resources:
	xcopy /E .\resources .\build\bin\resources

renderer.o: $(SRC_DIR)/renderer/renderer.cpp
	$(CC) $(CPPFLAGS) -c $< -o $(OBJ_DIR)/$@

board.o: $(SRC_DIR)/chess/board/board.cpp
	$(CC) $(CPPFLAGS) -c $< -o $(OBJ_DIR)/$@

application.o: $(SRC_DIR)/application/application.cpp
	$(CC) $(CPPFLAGS) -c $< -o $(OBJ_DIR)/$@

piece.o: $(SRC_DIR)/chess/piece/piece.cpp
	$(CC) $(CPPFLAGS) -c $< -o $(OBJ_DIR)/$@

king.o: $(SRC_DIR)/chess/piece/king/king.cpp
	$(CC) $(CPPFLAGS) -c $< -o $(OBJ_DIR)/$@

texture_manager.o: $(SRC_DIR)/texture_manager/texture_manager.cpp
	$(CC) $(CPPFLAGS) -c $< -o $(OBJ_DIR)/$@

app.exe: renderer.o texture_manager.o piece.o king.o board.o application.o
	$(eval OBJ_LOC := $(addprefix $(OBJ_DIR)/, $^))
	$(CC) -L ./library/SFML-2.5.1/lib -o $(BIN_DIR)/application.exe $(OBJ_LOC) -lsfml-graphics -lsfml-window -lsfml-system

build: copy_dependencies app.exe
	$(BIN_DIR)/application.exe

fresh_build: clean_all build