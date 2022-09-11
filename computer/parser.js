options:
    s: {state::%{_id}%}

# Parse string into program
function parser(string: string) :: nbtcompound:
    set {_id} to unix timestamp of now
    set {@s} to nbt compound from "{}"
    set string tag "string" of {@s} to {_string}
    set long tag "cursor" of {@s} to 1
    getNextToken({_id})
    return Program({_id})

# Program
#   - Literal
function Program(id: number) :: nbtcompound:
    set {_compound} to nbt compound from "{type:""Program""}"
    set compound tag "body" of {_compound} to Literal({_id})
    return {_compound}

# Literal
#   - NumberLiteral
#   - StringLiteral
function Literal(id: number) :: nbtcompound:
    set {_lookAhead} to compound tag "lookahead" of {@s}
    set {_type} to string tag "type" of {_lookAhead}

    if {_type} = "STRING":
        return StringLiteral({_id})
    else if {_type} = "NUMBER":
        return NumberLiteral({_id})
    else:
        broadcast "&cUnexpected literal"
        return {_}

# StringLiteral
#   - STRING
function StringLiteral(id: number) :: nbtcompound:
    set {_token} to consume("STRING", {_id})
    set {_compound} to nbt compound from "{type:""StringLiteral""}"
    set {_value} to string tag "value" of {_token}
    set string tag "value" of {_compound} to join (regex split {_value} at "\\(?="")")
    return {_compound}

# NumberLiteral
#   - NUMBER
function NumberLiteral(id: number) :: nbtcompound:
    set {_token} to consume("NUMBER", {_id})
    set {_compound} to nbt compound from "{type:""NumberLiteral""}"
    set {_value} to string tag "value" of {_token}
    set double tag "value" of {_compound} to ({_value} parsed as number)
    return {_compound}

function consume(type: string, id: number) :: nbtcompound:
    set {_lookAhead} to compound tag "lookahead" of {@s}
    if tag "type" of {_lookAhead} is not set:
        broadcast "&cUnexpected end of input, expected: '%{_type}%'"
    else if string tag "type" of {_lookAhead} != {_type}:
        broadcast "&cUnexpected token: %string tag "type" of {_lookAhead}%, expected: %{_type}%"
    else:
        getNextToken({@s})
        return {_lookAhead}

    return {_}
