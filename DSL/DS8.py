# Tic Tac Toe Game in Python

# Display the game board
def display_board(board):
    print(f"""
{board[0]} | {board[1]} | {board[2]}
---|---|---
{board[3]} | {board[4]} | {board[5]}
---|---|---
{board[6]} | {board[7]} | {board[8]}
""")

# Check for a win
def check_winner(board, player):
    win_combinations = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],  # Rows
        [0, 3, 6], [1, 4, 7], [2, 5, 8],  # Columns
        [0, 4, 8], [2, 4, 6]              # Diagonals
    ]
    for combo in win_combinations:
        if board[combo[0]] == board[combo[1]] == board[combo[2]] == player:
            return True
    return False

# Check for a draw
def check_draw(board):
    return " " not in board

# Main game loop
def play_game():
    board = [" "] * 9  # Empty board
    current_player = "X"  # Player X goes first
    print("Welcome to Tic Tac Toe!")
    display_board(board)
    
    while True:
        try:
            # Player move
            position = int(input(f"Player {current_player}, choose your position (1-9): ")) - 1
            if position < 0 or position > 8 or board[position] != " ":
                print("Invalid move. Try again.")
                continue
            
            # Update board
            board[position] = current_player
            display_board(board)
            
            # Check for winner or draw
            if check_winner(board, current_player):
                print(f"Player {current_player} wins!")
                break
            if check_draw(board):
                print("It's a draw!")
                break
            
            # Switch player
            current_player = "O" if current_player == "X" else "X"
        
        except ValueError:
            print("Please enter a valid number between 1 and 9.")

# Run the game
if __name__ == "__main__":
    play_game()
