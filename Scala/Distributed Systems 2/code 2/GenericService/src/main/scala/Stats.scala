class Stats {
  var messages: Int = 0
  var touches: Int = 0
  var misses: Int = 0
  var errors: Int = 0
  var joins: Int = 0
  var leaves: Int = 0
  var multicasted: Int = 0
  var multiCastReceived: Int = 0

  def += (right: Stats): Stats = {
    messages += right.messages
    touches += right.touches
    misses += right.misses
    errors += right.errors
    joins += right.joins
    leaves += right.leaves
    multicasted += right.multicasted
    multiCastReceived += right.multiCastReceived
    this
  }

  override def toString(): String = {
    s"Stats msgs=$messages joins=$joins leaves=$leaves multicasts=$multicasted multiCastReceived=$multiCastReceived miss=$misses err=$errors"
  }
}
